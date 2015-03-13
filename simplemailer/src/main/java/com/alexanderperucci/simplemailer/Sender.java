package com.alexanderperucci.simplemailer;

import javax.mail.MessagingException;

public abstract class Sender {
    private String from;
    private String to;
    private String messageSubject;
    private String messageContent;
    private String messageMIMEType;
    private String messageEncoding;
    private boolean debugMode;

    public Sender(String from, String to, String messageSubject, String messageContent, String messageMIMEType, String messageEncoding, boolean debugMode) {
        this.from = from;
        this.to = to;
        this.messageSubject = messageSubject;
        this.messageContent = messageContent;
        this.messageMIMEType = messageMIMEType;
        this.messageEncoding = messageEncoding;
        this.debugMode = debugMode;
    }
    
    public abstract void send() throws MessagingException;
    
    public String getMessageTypeContent(){
     return  messageMIMEType+"; charset="+messageEncoding;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageEncoding() {
        return messageEncoding;
    }

    public void setMessageEncoding(String messageEncoding) {
        this.messageEncoding = messageEncoding;
    }

    public String getMessageMIMEType() {
        return messageMIMEType;
    }

    public void setMessageMIMEType(String messageMIMEType) {
        this.messageMIMEType = messageMIMEType;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

}
