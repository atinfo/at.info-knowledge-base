package com.tools.qaa.utils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.IOUtils;

import javax.ws.rs.core.Response.Status;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.apache.commons.io.FilenameUtils.separatorsToSystem;
import static org.apache.commons.io.FilenameUtils.*;

public class FilesUtils {

    private static final Logger IO_LOGGER = Logger.getLogger(FilesUtils.class.getName());

    public static List<Status> saveFile(final InputStream fileStream, final String filePath, final boolean unZip) {
        final List<Status> responses = new ArrayList<>();

        try (final InputStream inputStream = fileStream;
             final FileOutputStream outputStream = new FileOutputStream(new File(filePath))) {

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            IO_LOGGER.info("File " + filePath + " has been saved.");
            responses.add(Status.OK);

            if (unZip) {
                responses.add(unZipItems(filePath));
            }
        } catch (IOException e) {
            IO_LOGGER.severe("Unable to save / extract file " + filePath + ": " + e.getMessage());
            responses.add(Status.INTERNAL_SERVER_ERROR);
        }

        return responses;
    }

    public static Status unZipItems(final String filePath) {
        return unZipItems(filePath, getFullPath(filePath) + getBaseName(filePath));
    }

    public static Status unZipItems(final String filePath, final String outputFolder) {
        Status response;

        if (getExtension(filePath).equals("zip")) {
            try {
                new ZipFile(filePath).extractAll(outputFolder);
                IO_LOGGER.info("All files have been successfully extracted!");
                response = Status.OK;
            } catch (ZipException e) {
                IO_LOGGER.severe("Unable extract files from " + filePath + ": " + e.getMessage());
                response = Status.INTERNAL_SERVER_ERROR;
            }
        } else {
            response = Status.NOT_FOUND;
        }

        return response;
    }

    public static String formatPath(final String fileName, final String saveToPath) {
        return separatorsToSystem(saveToPath + File.separator + fileName);
    }
}
