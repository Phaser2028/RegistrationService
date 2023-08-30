package com.example.SpringSecurityRegistrationService.service;

import com.example.SpringSecurityRegistrationService.model.Person;
import org.springframework.web.multipart.MultipartFile;


public interface RegistrationService {
    Boolean activateUser(String code);
    void register(Person person, MultipartFile file);
}
