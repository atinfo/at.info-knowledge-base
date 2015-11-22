package com.blogspot.notes.automation.qa.client;

import com.blogspot.notes.automation.qa.entities.CommonTask;
import com.blogspot.notes.automation.qa.entities.JavaTask;
import com.blogspot.notes.automation.qa.utils.ObjectMapperProvider;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by Serhii Kuts
 */
public class WatcherClient {
    private Client client;
    private WebTarget service;

    private static final int PROCESS_WAIT_TIMEOUT = 3;

    private static final Logger CLIENT_LOGGER = Logger.getLogger(WatcherClient.class.getName());

    public WatcherClient(final String ip, final int port) {
        client = ClientBuilder.newBuilder()
                .register(ObjectMapperProvider.class)
                .register(JacksonFeature.class)
                .build();

        service = client.target("http://" + ip + ":" + port);
    }

    public void disconnect() {
        if (client != null) {
            client.close();
        }
    }

    public boolean killJavaTasks(final JavaTask... tasks) {
        return killJavaTasks(Arrays.asList(tasks)
                .stream()
                .map(JavaTask::toString)
                .collect(Collectors.toList()));
    }

    public boolean killJavaTasks(final List<String> tasks) {
        Response response = null;
        boolean isFinished;

        try {
            response = service.path("shutdown")
                    .path("javaTasks")
                    .queryParam("quoteArgs", false)
                    .queryParam("timeout", PROCESS_WAIT_TIMEOUT)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(tasks));

            isFinished = response.getStatus() == Response.Status.OK.getStatusCode();
            CLIENT_LOGGER.info("The following java tasks killed: " + Arrays.asList(tasks));
        } catch (Exception ex) {
            isFinished = false;
            CLIENT_LOGGER.severe("Can't kill java tasks: " + ex);
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return isFinished;
    }

    public boolean killCommonTasks(final CommonTask... tasks) {
        return killCommonTasks(Arrays.asList(tasks)
                .stream()
                .map(CommonTask::toString)
                .collect(Collectors.toList()));
    }

    public boolean killCommonTasks(final List<String> tasks) {
        Response response = null;
        boolean isFinished;

        try {
            response = service.path("shutdown")
                    .path("commonTasks")
                    .queryParam("quoteArgs", false)
                    .queryParam("timeout", PROCESS_WAIT_TIMEOUT)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(tasks));

            isFinished = response.getStatus() == Response.Status.OK.getStatusCode();
            CLIENT_LOGGER.info("The following common tasks killed: " + Arrays.asList(tasks));
        } catch (Exception ex) {
            isFinished = false;
            CLIENT_LOGGER.severe("Can't kill common tasks: " + ex);
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return isFinished;
    }

    public boolean startScript(final String path) {
        Response response = null;
        boolean isFinished;

        try {
            response = service.path("cmd")
                    .path("startScript")
                    .queryParam("path", path)
                    .queryParam("quoteArgs", false)
                    .queryParam("timeout", PROCESS_WAIT_TIMEOUT)
                    .request(MediaType.APPLICATION_JSON)
                    .post(null);

            isFinished = response.getStatus() == Response.Status.OK.getStatusCode();
            CLIENT_LOGGER.info("The following script started: " + path);
        } catch (Exception ex) {
            isFinished = false;
            CLIENT_LOGGER.severe("Can't start a script: " + ex);
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return isFinished;
    }

    public boolean updateLastIpInFile(final String filePath, final String newIp) {
        Response response = null;
        boolean isFinished;

        try {
            response = service.path("cmd")
                    .path("updateIP")
                    .queryParam("filePath", filePath)
                    .queryParam("newIp", newIp)
                    .request(MediaType.APPLICATION_JSON)
                    .post(null);

            isFinished = response.getStatus() == Response.Status.OK.getStatusCode();
            CLIENT_LOGGER.info(filePath + " was updated with the following ip: " + newIp);
        } catch (Exception ex) {
            isFinished = false;
            CLIENT_LOGGER.severe("Can't update ip in a file: " + ex);
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return isFinished;
    }

    public boolean minimizeWindows() {
        Response response = null;
        boolean isFinished;

        try {
            response = service.path("cmd")
                    .path("minimizeWindows")
                    .queryParam("quoteArgs", true)
                    .queryParam("timeout", PROCESS_WAIT_TIMEOUT)
                    .request(MediaType.APPLICATION_JSON)
                    .post(null);

            isFinished = response.getStatus() == Response.Status.OK.getStatusCode();
            CLIENT_LOGGER.info("All windows were minimized.");
        } catch (Exception ex) {
            isFinished = false;
            CLIENT_LOGGER.severe("Can't minimize windows: " + ex);
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return isFinished;
    }
}
