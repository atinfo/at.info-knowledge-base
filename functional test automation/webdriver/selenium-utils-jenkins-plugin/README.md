Jenkins plugin example: how to shutdown Selenium Grid hub / nodes
======

Implemented via maven, jersey.

Main usage: releasing hub / nodes to minimize possible Selenium Grid stucking.
You can find detailed instruction here: http://qa-automation-notes.blogspot.com/2014/09/jenkins-plugin-for-killing-selenium-grid.html 
http://automated-testing.info/t/code-recipe-uchimsya-sozdavat-jenkins-plaginy-na-primere-selenium-grid-killera/5108

Inside of project you will see: 
 
 - Selenium REST client.
 - Plugin's layout configuration and appropriate java handlers.

REST API for killing hub / nodes looks like the following:
```java
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
```

Jenkins plugin will look like the following:

![plugin layout](http://1.bp.blogspot.com/-lkxZZVd0hEI/VCRoWuiYkfI/AAAAAAAAAZU/jwxnf8dOyn8/s1600/plugin%2Bconfig.png)
