package com.alexanderperucci.simplemailer;

import com.alexanderperucci.simplemailer.impl.Reporter;
import com.alexanderperucci.simplemailer.impl.configuration.LoadPropertiesFile;
import com.alexanderperucci.simplemailer.impl.sender.SMTPSender;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//
/**
 *
 * @author alexander
 */
public class SimpleMailer {

    private static Logger logger = LoggerFactory.getLogger(Reporter.class);

    private static final String MAIL_CONFIG = "config.properties";

    public static void main(String[] args) throws IOException {

        LoadPropertiesFile mailConfig = new LoadPropertiesFile(MAIL_CONFIG);
        String messageMIMEType = mailConfig.get("mail.message.mimetype");
        String messageEncoding = mailConfig.get("mail.message.encoding");
        String messageSubject = mailConfig.get("mail.message.subject");
        String from = mailConfig.get("mail.from");
        List<String> tos = FileUtils.readLines(new File(mailConfig.get("mail.to.file")), messageEncoding);
        String messageContent = FileUtils.readFileToString(new File(mailConfig.get("mail.message.content.file")), messageEncoding);

        boolean debugMode = false;
        if (mailConfig.get("mail.debug") != null && mailConfig.get("mail.debug").compareTo("true") == 0) {
            debugMode = true;
        }

        Reporter reporter = new Reporter();
        for (String to : tos) {
            try {
                Sender sender = new SMTPSender(from, to, messageSubject, messageContent, messageMIMEType, messageEncoding, debugMode, reporter);
                sender.send();
            } catch (MessagingException e) {
                logger.error("ERROR DETECTED:", e);
            }
        }

        if (mailConfig.get("mail.report.file") != null || !mailConfig.get("mail.report.file").isEmpty()) {
            File reportFile = new File(mailConfig.get("mail.report.file"));
            FileUtils.writeStringToFile(reportFile, reporter.getReport());
        }

    }

}
