package com.tools.qaa.samples;

import com.tools.qaa.client.SeleniumContentSupplier;
import com.tools.qaa.interfaces.Content.SeleniumContent;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class SeleniumContentTests {

    private SeleniumContentSupplier localSupplier;
    private SeleniumContentSupplier remoteSupplier;

    private static final String OUTPUT_PATH = "C:\\Drivers";

    @BeforeClass
    public void setUp() {
        localSupplier = new SeleniumContentSupplier();
        remoteSupplier = new SeleniumContentSupplier("127.0.0.1", 4043);
    }

    @AfterClass
    public void dispose() {
        localSupplier.closeConnection();
        remoteSupplier.closeConnection();
    }

    @Test
    public void downloadAndUnZipFilesLocally() {
        Stream.of(SeleniumContent.values())
                .parallel()
                .forEach(content -> localSupplier.downloadAndUnZipFile(content, OUTPUT_PATH));
    }

    @Test
    public void downloadFilesLocallyAndSendToRemote() {
        Arrays.asList(SeleniumContent.SELENIUM_SERVER, SeleniumContent.CHROME_DRIVER_WIN32, SeleniumContent.IE_DRIVER_X32)
                .parallelStream()
                .forEach(content -> remoteSupplier.sendFileToRemoteAndUnZip(Collections.singletonList(
                        remoteSupplier.downloadAndUnZipFile(content, OUTPUT_PATH)), OUTPUT_PATH));
    }
}
