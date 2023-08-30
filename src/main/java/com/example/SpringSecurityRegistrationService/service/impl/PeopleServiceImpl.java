package com.example.SpringSecurityRegistrationService.service.impl;

import com.example.SpringSecurityRegistrationService.model.Person;
import com.example.SpringSecurityRegistrationService.repository.PeopleRepository;
import com.example.SpringSecurityRegistrationService.security.PersonDetails;
import com.example.SpringSecurityRegistrationService.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeopleServiceImpl implements PeopleService {

    private final PeopleRepository peopleRepository;

    public PeopleServiceImpl(@Autowired PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public PersonDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByEmail(email);

        if (person.isEmpty())
            throw new UsernameNotFoundException("Username not found");

        return new PersonDetails(person.get());
    }

    @Override
    public Optional<Person> getPersonByActivationCode(String code) {
        return peopleRepository.findByActivationCode(code);
    }

}
