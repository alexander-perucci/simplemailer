Simplemailer
==================

Simplemailer is a maven Java Application to send an email to a single user or to a list of users and reports you the list of emails that have received or not your message.
Simplemailer is simple to use, you only have to configure the protocol and other properties to setup the application. 
In order to configure properly the application you have to set the value of the property in the files: `config.properties` and `protocol.smtp.properties`
Simplemailer sends the email using the `SMTP` protocol, but can be easly extended to use other protocol, to do this can you seen the `SMTPSender.java` class.


## Getting Started

### Downloading Simplemailer

Simplemailer can be downloaded [here](https://github.com/prednaxela/simplemailer/archive/master.zip)

### Dependencies

Simplemailer is a Maven application. Can you ownload maven [here](http://maven.apache.org/download.cgi)
Simplemailer needs the following dependencies that you found in the `POM` file located in `simplemailer/pom.xml`:
 1. Java [version 1.7]
 2. javax.mail [version 1.5.2]
 3. Commons-io [version 2.4]
 4. slf4j-api [version 1.6.4]
 5. slf4j-log4j12 [version 1.6.4]

## Step to use simplemailer
In order to send an email to a list of emails you have to perform this simple steps:

### First steps
 1. Download simplemailer [here](https://github.com/prednaxela/simplemailer/archive/master.zip)
 2. Create a file with the contents of the email
 3. Create a file that contains a list of emails to send your message. Please take care to put the emails one per line

### Configure `config.properties` file
 1. Locate the properties file in `simplemailer/src/main/resources`
 2. Set the following property:
 	* mail.from
 	* mail.message.subject
 	* mail.to.file
 	* mail.message.content.file
 	* mail.report.file

### Configure `protocol.smtp.properties` file
 1. Locate the properties file in `simplemailer/src/main/resources`
 2. Set the following property:
 	* mail.smtp.host
 	* mail.smtp.user
 	* mail.smtp.password
 	* mail.smtp.port
 	* mail.smtp.auth

### Send the contents of the email to your list
 1. Locate the `simplemailer` folder
 2. Execute the maven command: <pre>mvn clean compile install</pre>
 3. Execute the maven command: <pre>mvn exec:java</pre>

## Reporting an Issue

1. Make sure the problem you're addressing is reproducible.

## Contribute
You can also support this project by donating on Gratipay [here](https://www.gratipay.com/prednaxela/)

## License
Licensed under the MPL version 2.0 license.

Copyright (c) 2014 Alexander Perucci.
