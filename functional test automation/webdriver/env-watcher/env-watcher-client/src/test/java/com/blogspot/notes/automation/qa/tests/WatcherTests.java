package com.blogspot.notes.automation.qa.tests;

import com.blogspot.notes.automation.qa.client.WatcherClient;
import com.blogspot.notes.automation.qa.entities.CommonTask;
import com.blogspot.notes.automation.qa.entities.JavaTask;
import org.testng.annotations.Test;

/**
 * Created by Sergey Kuts
 */
public class WatcherTests {

    @Test
    public void restartServices() {

        final WatcherClient client = new WatcherClient("127.0.0.1", 4041);
        client.killJavaTasks(JavaTask.SELENIUM);
        client.killCommonTasks(CommonTask.CHROME, CommonTask.FIREFOX, CommonTask.IE_BROWSER, CommonTask.IE_DRIVER);
        client.startScript("C:\\Grid\\start.bat");
        client.minimizeWindows();
        client.disconnect();
    }
}
