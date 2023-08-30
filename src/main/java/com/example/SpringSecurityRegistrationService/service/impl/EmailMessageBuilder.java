package com.example.SpringSecurityRegistrationService.service.impl;


import com.example.SpringSecurityRegistrationService.config.MailProperty;
import com.example.SpringSecurityRegistrationService.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailMessageBuilder {
    @Autowired
    private MailProperty mailProperty;

    public SimpleMailMessage build(Person person){

        String messageText = "Активация: http://localhost:8080/auth/activate/" + person.getActivationCode();

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(mailProperty.getUsername());
        message.setTo(person.getEmail());
        message.setSubject("Activation code");
        message.setText(messageText);

        return message;
    }
}
