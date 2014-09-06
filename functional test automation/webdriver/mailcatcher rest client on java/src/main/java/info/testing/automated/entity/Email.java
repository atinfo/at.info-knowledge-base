package info.testing.automated.entity;

import java.util.Date;
import java.util.List;

/**
 * Author: Serhii Kuts
 * Date: 9/4/2014
 * Time: 4:59 PM
 */
public class Email {

    private int id;
    private String sender;
    private List<String> recipients;
    private String subject;
    private String size;
    private Date created_at;

    private String source;
    private String type;
    private List<String> formats;
    private List<String> attachments;

    public Email() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getFormats() {
        return formats;
    }

    public void setFormats(List<String> formats) {
        this.formats = formats;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "[id : " + getId() +
                "; sender : " + getSender() +
                "; recipients : " + getRecipients() +
                "; subject : " + getSubject() +
                "; size : " + getSize() +
                "; created at : " + getCreated_at() +
                "; source : " + getSource() +
                "; formats : " + getFormats() +
                "; attachments : " + getAttachments() +
                "; type : " + getType() +
                "]";
    }
}

