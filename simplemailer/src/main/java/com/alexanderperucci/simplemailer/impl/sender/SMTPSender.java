package com.alexanderperucci.simplemailer.impl.sender;

import com.alexanderperucci.simplemailer.Sender;
import com.alexanderperucci.simplemailer.impl.Reporter;
import com.alexanderperucci.simplemailer.impl.configuration.LoadPropertiesFile;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SMTPSender extends Sender {

    private static final String PROTOCOL_SMTP_CONFIGURATION_FILE = "protocol.smtp.properties";
    private static final String PROTOCOL = "smtp";
    
    private Reporter reporter;
    
    public SMTPSender(String from, String to, String messageSubject, String messageContent, String messageMIMEType, String messageEncoding, boolean debugMode, Reporter reporter) {
        super(from, to, messageSubject, messageContent, messageMIMEType, messageEncoding, debugMode);
        this.reporter=reporter;
    }

    @Override
    public void send() throws MessagingException{
        LoadPropertiesFile protocolConfiguration = new LoadPropertiesFile(PROTOCOL_SMTP_CONFIGURATION_FILE);
        Session session = Session.getDefaultInstance(protocolConfiguration.getProperties());
        session.setDebug(super.isDebugMode());

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(super.getFrom()));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(super.getTo()));
        message.setSubject(super.getMessageSubject());
        message.setContent(super.getMessageContent(), super.getMessageTypeContent());

        // Send message
        Transport transport = session.getTransport(PROTOCOL);
        transport.addTransportListener(reporter);
        transport.connect(protocolConfiguration.get("mail.smtp.host"), super.getFrom(), protocolConfiguration.get("mail.smtp.password"));
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();

    }

}
