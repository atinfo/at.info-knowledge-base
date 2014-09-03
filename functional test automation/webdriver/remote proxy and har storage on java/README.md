Java BrowserMob proxy with HAR storage example: how to use remote proxy and save output HAR files into storage
======

Implemented via maven, jersey, testng.

Main usage: capturing network traffic, performance analysis, server / JS error investigation.
You can find detailed instruction here: http://qa-automation-notes.blogspot.com/2014/09/monitoring-network-activity-via.html and related discussions here http://automated-testing.info/t/ispolzovanie-remote-browsermob-proxy-har-storage/5009

Inside of project you will see: 
 
 - BMP REST client.
 - HAR storage REST client.
 - Custom annotation for activating proxy.
 - BaseTest implementation for setting up driver / proxy and storage.
 - Simple test implementation with tracking network traffic and putting HAR into storage.

Sample HAR retrieving and saving requests look like the following:
```java
    public String getHarAsString() {
        return service.path(Integer.toString(port))
                .path("har")
                .request()
                .get(Response.class)
                .readEntity(String.class);
    }

    public String save(final String data) {
        final Form form = new Form().param("file", data);

        return service.path("upload")
                .request(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .header("Automated", "true")
                .post(Entity.form(form))
                .readEntity(String.class);
    }		
```
