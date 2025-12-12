package com.group3.petcareorganizer.service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/* @Service means this class is a Spring service, it handles email sending
 */
@Service
public class EmailService {

    // uses JavaMailSender to create email capabilities
    private final JavaMailSender mailSender;

    /* constructor for email service
     */
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /* sends email to the user, with the event name as subject, email body has event time and date info
     */
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

}
