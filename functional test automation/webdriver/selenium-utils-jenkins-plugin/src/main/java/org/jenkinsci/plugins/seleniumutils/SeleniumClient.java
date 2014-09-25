package org.jenkinsci.plugins.seleniumutils;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Author: Serhii Kuts
 */
public class SeleniumClient {
    private Client client;

    public SeleniumClient() {
        client = ClientBuilder.newBuilder()
                .register(JacksonFeature.class)
                .build();
    }

    public boolean shutDownHub(final String ip, final int port) {
        return client != null && client.target("http://" + ip + ":" + port)
                .path("lifecycle-manager")
                .queryParam("action", "shutdown")
                .request(MediaType.APPLICATION_JSON)
                .get()
                .getStatus() == Response.Status.OK.getStatusCode();
    }

    public boolean shutDownNode(final String ip, final int port) {
        return client != null && client.target("http://" + ip + ":" + port)
                .path("selenium-server")
                .path("driver")
                .queryParam("cmd", "shutDownSeleniumServer")
                .request(MediaType.APPLICATION_JSON)
                .post(null).getStatus() == Response.Status.OK.getStatusCode();

    }

    public void disconnect() {
        if (client != null) {
            client.close();
        }
    }
}
