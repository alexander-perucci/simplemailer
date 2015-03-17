package com.alexanderperucci.simplemailer.impl;

import com.alexanderperucci.simplemailer.MessageStatus;

import java.util.Arrays;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author alexander
 */
public class Reporter implements TransportListener {
    
    private static Logger logger = LoggerFactory.getLogger(Reporter.class);
    
    StringBuilder report;
    
    public Reporter(){
        report= new StringBuilder();
    }
    
    @Override
    public void messageDelivered(TransportEvent transportEvent) {
        //Message has been successfully delivered to all recipients by the transport firing this event. 
        //validSent[] contains all the addresses this transport sent to successfully. 
        //validUnsent[] and invalid[] should be null.
        logEvent(transportEvent, MessageStatus.MESSAGE_DELIVERED);
    }

    @Override
    public void messageNotDelivered(TransportEvent transportEvent) {
        //Message was not sent for some reason.
        //validSent[] should be null.
        //validUnsent[] may have addresses that are valid (but the message wasn't sent to them).
        //invalid[] should likely contain invalid addresses.
        logEvent(transportEvent, MessageStatus.MESSAGE_NOT_DELIVERED);
    }

    @Override
    public void messagePartiallyDelivered(TransportEvent transportEvent) {
        //Message was successfully sent to some recipients but not to all.
        //validSent[] holds addresses of recipients to whom the message was sent.
        //validUnsent[] holds valid addresses to which the message was not sent.
        //invalid[] holds invalid addresses, if any.
        logEvent(transportEvent, MessageStatus.MESSAGE_PARTIALLY_DELIVERED);
        
    }
    
    public void logEvent(TransportEvent transportEvent, MessageStatus messageStatus) {
        String entryLog = createEntryLog(transportEvent, messageStatus);
        report.append(entryLog);
        logger.info(entryLog);
    }
    
    private String createEntryLog(TransportEvent transportEvent, MessageStatus messageStatus){
        StringBuilder addresses = new StringBuilder();
        try {
            for (Address address : Arrays.asList(transportEvent.getMessage().getRecipients(Message.RecipientType.TO))){
                addresses.append(address.toString()+" -- "+messageStatus.toString()+", ");
            }
            addresses.replace(addresses.indexOf(", "), addresses.length(), "") ;
            addresses.append(System.getProperty("line.separator"));
            
        } catch (MessagingException e) {
            logger.error("ERROR DETECTED:",e);
        }
        return addresses.toString();
    }
    
    public String getReport() {
        return report.toString();
    }
}
