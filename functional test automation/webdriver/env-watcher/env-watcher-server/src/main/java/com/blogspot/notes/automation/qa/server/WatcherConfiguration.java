package com.blogspot.notes.automation.qa.server;

import com.blogspot.notes.automation.qa.services.CommandLineService;
import com.blogspot.notes.automation.qa.services.ShutdownService;
import com.blogspot.notes.automation.qa.utils.ObjectMapperProvider;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Author: Serhii Kuts
 */
public class WatcherConfiguration extends ResourceConfig {
    public WatcherConfiguration() {
        super(ObjectMapperProvider.class, CommandLineService.class, ShutdownService.class, JacksonFeature.class);
    }
}
