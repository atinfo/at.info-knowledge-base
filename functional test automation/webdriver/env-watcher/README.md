Environment Watcher - service for handling stuck automation processes
======

Implemented via maven, jersey, apache-commons-exec.

Main usage: killing browsers with their drivers / java processes (grid / sikuli / bmp) via Jenkins plugin https://github.com/sskorol/selenium-utils.

You can find detailed instruction here: http://qa-automation-notes.blogspot.com/2015/02/environment-watcher-or-how-to-create.html

Project structure: 
 
 - Watcher core, that contains some common entities / interfaces / utils.
 - Watcher REST client.
 - Watcher server, which support the following services: command line / shutdown.

REST API example for killing java tasks:

```java

    public boolean killJavaTasks(final JavaTask... tasks) {
        return killJavaTasks(Arrays.asList(tasks)
                .stream()
                .map(JavaTask::toString)
                .collect(Collectors.toList()));
    }

    public boolean killJavaTasks(final List<String> tasks) {
        Response response = null;
        boolean isFinished;

        try {
            response = service.path("shutdown")
                    .path("javaTasks")
                    .queryParam("quoteArgs", false)
                    .queryParam("timeout", PROCESS_WAIT_TIMEOUT)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(tasks));

            isFinished = response.getStatus() == Response.Status.OK.getStatusCode();
            CLIENT_LOGGER.info("The following java tasks killed: " + Arrays.asList(tasks));
        } catch (Exception ex) {
            isFinished = false;
            CLIENT_LOGGER.severe("Can't kill java tasks: " + ex);
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return isFinished;
    }
```

Jenkins plugin output:

![plugin output](http://2.bp.blogspot.com/-AVgZSa2YsZg/VNfF0FqpS6I/AAAAAAAAAnA/ZooqgKDPpIw/s1600/log.png)
