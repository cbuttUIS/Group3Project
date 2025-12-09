package com.group3.petcareorganizer.controller;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class EmailTestController {

    private final JavaMailSender mailSender;

    public EmailTestController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @GetMapping("/send-email")
    public String sendTestEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("cbutton0310@gmail.com");
            message.setSubject("Test Email from Docker App");
            message.setText("This is a test email from your Spring Boot Docker container.");
            mailSender.send(message);
            return "Email sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send email: " + e.getMessage();
        }
    }
}