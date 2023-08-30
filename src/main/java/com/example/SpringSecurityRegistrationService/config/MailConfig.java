package com.example.SpringSecurityRegistrationService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    private final MailProperty mailProperty;

    public MailConfig(MailProperty mailProperty) {
        this.mailProperty = mailProperty;
    }

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(mailProperty.getHost());
        mailSender.setPort(mailProperty.getPort());
        mailSender.setPassword(mailProperty.getPassword());
        mailSender.setUsername(mailProperty.getUsername());


        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

}
