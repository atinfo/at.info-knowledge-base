package com.tools.qaa.client;

import com.tools.qaa.services.FileService;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class ServerConfiguration extends ResourceConfig {

    public ServerConfiguration() {
        super(MultiPartFeature.class, FileService.class);
    }
}
