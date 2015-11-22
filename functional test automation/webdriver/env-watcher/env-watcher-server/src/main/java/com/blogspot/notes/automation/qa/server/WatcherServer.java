package com.blogspot.notes.automation.qa.server;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.lang.math.NumberUtils;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.net.InetAddress;
import java.net.URI;
import java.util.logging.Logger;

/**
 * Author: Serhii Kuts
 */
public class WatcherServer {
    private static final int DEFAULT_PORT = 4041;
    private static final Logger SERVER_LOGGER = Logger.getLogger(WatcherServer.class.getName());

    public static void startServer(final int port) throws Exception {
        final URI baseUri = URI.create("http://" + InetAddress.getLocalHost().getHostAddress() + ":" + port);
        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, new WatcherConfiguration());

        System.in.read();
        server.shutdown();
    }

    public static void main(final String[] args) {
        final Options commonOptions = new Options()
                .addOption("port", true, "remote watcher service port; default - 4041");

        try {
            final String port = new DefaultParser().parse(commonOptions, args).getOptionValue("port", "4041");
            startServer(args.length > 0 && NumberUtils.isNumber(port) ? Integer.parseInt(port) : DEFAULT_PORT);
        } catch (Exception e) {
            SERVER_LOGGER.severe("Can't launch server: " + e);
        }
    }
}
