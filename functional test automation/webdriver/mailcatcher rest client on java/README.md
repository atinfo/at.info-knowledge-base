MailCatcher Java REST client example: how to use MailCatcher REST API for emails testing
======

Implemented via maven, jersey, testng.

Main usage: emails testing.

You can find detailed instruction here: http://qa-automation-notes.blogspot.com/2014/09/mailcatcher-rest-client-for-emails.html

Inside of project you will see: 
 
 - MailCatcher REST client.
 - Email POJO.
 - Json utils.
 - Simple test for demonstrating main APIs.

Sample emails retrieving and deleting requests look like the following:
```java
    public List<Email> getEmails() {
        final List<Email> emails = fromJSON(new TypeReference<List<Email>>() {},
                service.path("messages")
                        .request(MediaType.APPLICATION_JSON)
                        .get()
                        .readEntity(String.class));

        Collections.sort(emails, new Comparator<Email>() {
            @Override
            public int compare(Email email1, Email email2) {
                if (email1.getId() < email2.getId()) {
                    return 1;
                } else if (email1.getId() > email2.getId()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        return emails;
    }

    public boolean deleteEmails() {
        return service.path("messages")
                .request(MediaType.APPLICATION_JSON)
                .delete()
                .getStatus() == HTTP_RESPONSE_NO_CONTENT;
    }	
```