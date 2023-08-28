package com.example.SpringSecurityRegistrationService.service.impl;


import com.example.SpringSecurityRegistrationService.config.MailProperty;
import com.example.SpringSecurityRegistrationService.exception.ProfilePictureException;
import com.example.SpringSecurityRegistrationService.model.Person;
import com.example.SpringSecurityRegistrationService.repository.PeopleRepository;
import com.example.SpringSecurityRegistrationService.service.RegistrationService;
import com.example.SpringSecurityRegistrationService.util.Imager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final PeopleRepository peopleRepository;
    private final Imager imager;

    private final JavaMailSender emailSender;

    private final MailProperty mailProperty;


    @Autowired
    public RegistrationServiceImpl(PasswordEncoder passwordEncoder, PeopleRepository peopleRepository, Imager imager, JavaMailSender emailSender, MailProperty mailProperty) {
        this.passwordEncoder = passwordEncoder;
        this.peopleRepository = peopleRepository;
        this.imager = imager;
        this.emailSender = emailSender;
        this.mailProperty = mailProperty;
    }


    @Override
    public void register(Person person, MultipartFile file) {


        try {
            imager.setProfilePicture(person, file);
        } catch (IOException e) {
            throw new ProfilePictureException("Something went wrong.");
        }


        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");

        person.setActivationCode(UUID.randomUUID().toString());

        String messageText = "Активация: http://localhost:8080/auth/activate/" + person.getActivationCode();


        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(mailProperty.getUsername());
        System.out.println(mailProperty.getUsername());
        message.setTo(person.getEmail());
        message.setSubject("Activation code");
        message.setText(messageText);


        this.emailSender.send(message);
        peopleRepository.save(person);
    }

    @Override
    public Boolean activateUser(String code) {
        Optional<Person> person = peopleRepository.findByActivationCode(code);

        if (person.isEmpty()) {
            return false;
        }

        person.get().setActivationCode(null);
        peopleRepository.save(person.get());
        return true;
    }

}
