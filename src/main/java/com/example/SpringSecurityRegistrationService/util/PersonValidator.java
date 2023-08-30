package com.example.SpringSecurityRegistrationService.util;

import com.example.SpringSecurityRegistrationService.dto.PersonDTO;
import com.example.SpringSecurityRegistrationService.model.Person;
import com.example.SpringSecurityRegistrationService.repository.PeopleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;


    public PersonValidator(PasswordEncoder passwordEncoder, PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        PersonDTO personDTO = (PersonDTO) o;

        if (peopleRepository.findByEmail(personDTO.getEmail()).isPresent()) {
            errors.rejectValue("email", "1", "Человек с таким email уже существует");
        }


        if(!doPasswordsMatch(
                personDTO.getPassword(),
                passwordEncoder.encode(personDTO.getConfirmPassword()))
        ) {
            errors.rejectValue("password", "2","Пароли не совпадают");
        }

    }

    private boolean doPasswordsMatch(String rawPassword,String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
