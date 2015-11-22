package com.tools.qaa.client;

import com.tools.qaa.entities.xml.ContentsType;
import com.tools.qaa.entities.xml.ListBucketResultType;
import com.tools.qaa.interfaces.Content;
import com.tools.qaa.interfaces.Content.SeleniumContent;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import static org.apache.commons.io.FilenameUtils.*;
import static org.apache.commons.io.IOUtils.*;
import static com.tools.qaa.utils.FilesUtils.*;

public class SeleniumContentSupplier {

    private Client client;
    private String ip;
    private int port;

    private static final Logger CLIENT_LOGGER = Logger.getLogger(SeleniumContentSupplier.class.getName());

    public SeleniumContentSupplier() {
        client = ClientBuilder.newBuilder()
                .register(MultiPartFeature.class)
                .build();
    }

    public SeleniumContentSupplier(final String ip, final int port) {
        this();
        this.ip = ip;
        this.port = port;
    }

    public void closeConnection() {
        Optional.ofNullable(client)
                .ifPresent(Client::close);
    }

    public String downloadAndUnZipFile(final SeleniumContent contentType, final String localFolder) {
        final String filePath = downloadFile(contentType, localFolder);
        unZipItems(filePath);
        return filePath;
    }

    public String downloadFile(final SeleniumContent contentType, final String localFolder) {
        final String[] latestPath = getLatestPath(contentType.getContent())
                .map(path -> path.split("/"))
                .orElse(new String[0]);

        if (latestPath.length < 2) {
            throw new IllegalArgumentException("Unable to parse provided end-point path: " + Arrays.asList(latestPath));
        }

        final String version = latestPath[0];
        final String fileName = latestPath[1];
        String prettifiedFileName = separatorsToSystem(Optional.ofNullable(localFolder)
                .orElse(System.getProperty("user.home")) + File.separator + fileName);

        CLIENT_LOGGER.info("Detected '" + fileName + "' -> Trying to download " + version + " version...");

        try (final InputStream inputStream = client.target(contentType.getContent().getUrl())
                .path(version)
                .path(fileName)
                .request()
                .get(InputStream.class);
             final FileOutputStream fileOutputStream = new FileOutputStream(new File(prettifiedFileName))) {

            copy(inputStream, fileOutputStream);
            fileOutputStream.flush();

            CLIENT_LOGGER.info("'" + prettifiedFileName + "' has been successfully saved!");
        } catch (Exception e) {
            CLIENT_LOGGER.severe("Unable to save a file: " + e.getMessage());
            prettifiedFileName = "";
        }

        return prettifiedFileName;
    }

    public void sendFileToRemoteAndUnZip(final List<String> filesPath, final String saveToPath) {
        Response response = null;
        final MultiPart multiPart = new MultiPart(MediaType.MULTIPART_FORM_DATA_TYPE);

        filesPath.stream().forEach(path -> multiPart.bodyPart(new FileDataBodyPart("file",
                new File(separatorsToSystem(path)), MediaType.APPLICATION_OCTET_STREAM_TYPE)));

        try {
            response = client.target("http://" + ip + ":" + port + "/selenium")
                    .path("content")
                    .path("upload")
                    .queryParam("saveTo", separatorsToSystem(saveToPath))
                    .queryParam("unZip", true)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(multiPart, multiPart.getMediaType()));

            final int status = response.getStatus();

            if (status == Response.Status.OK.getStatusCode()) {
                CLIENT_LOGGER.info("File(-s) " + filesPath + " has been saved to " + separatorsToSystem(saveToPath) + " on " + ip);
            } else {
                CLIENT_LOGGER.severe("Unable to save or unZip file(-s) " + filesPath + " to " + separatorsToSystem(saveToPath) + " on " + ip + "; status = " + status);
            }
        } catch (Exception e) {
            CLIENT_LOGGER.info("An error occurred while file(-s) uploading: " + e);
            response = null;
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    private Optional<String> getLatestPath(final Content contentType) {
        try {
            return Optional.ofNullable(client.target(contentType.getUrl())
                    .request(MediaType.APPLICATION_XML)
                    .get()
                    .readEntity(ListBucketResultType.class))
                    .map(contentContainer -> getLatestPath(contentContainer, contentType))
                    .orElse(Optional.<String>empty());
        } catch (Exception e) {
            CLIENT_LOGGER.severe("Unable to get resource content: " + e.getMessage());
            return Optional.empty();
        }
    }

    private Optional<String> getLatestPath(final ListBucketResultType contentContainer, final Content contentType) {
        return contentContainer.getContents().stream()
                .map(ContentsType::getKey)
                .filter(key -> key.contains(contentType.getName()))
                .reduce((key1, key2) -> key2);
    }
}
