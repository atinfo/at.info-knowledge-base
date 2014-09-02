package info.testing.automated.proxy;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

/**
 * Author: Serhii Kuts
 * Date: 2/4/14
 * Time: 10:58 PM
 */
public class HarStorage {

    private String targetURL;
    private WebTarget service;

    public HarStorage(final String host, final String port) {
        this.targetURL = "http://" + host + ":" + port + "/results";
        this.service = ClientBuilder.newClient().target(targetURL);
    }

    public String save(final String data) {
        final Form form = new Form().param("file", data);

        return service.path("upload")
                .request(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .header("Automated", "true")
                .post(Entity.form(form))
                .readEntity(String.class);
    }

    public String getHarDetailsURL(final String harName) {
        try {
            return URIUtil.encodeQuery(targetURL + "/details?label=" + harName);
        } catch (URIException e) {
            return null;
        }
    }
}

