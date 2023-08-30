package com.example.SpringSecurityRegistrationService.service;

import com.example.SpringSecurityRegistrationService.model.Person;
import com.example.SpringSecurityRegistrationService.security.PersonDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface PeopleService extends UserDetailsService {
    PersonDetails loadUserByUsername(String username);//без уточнения возвращает UserDetails
    Optional<Person> getPersonByActivationCode(String code);
}
