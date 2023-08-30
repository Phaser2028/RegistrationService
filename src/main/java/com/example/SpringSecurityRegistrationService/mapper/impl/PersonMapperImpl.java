package com.example.SpringSecurityRegistrationService.mapper.impl;

import com.example.SpringSecurityRegistrationService.dto.PersonDTO;
import com.example.SpringSecurityRegistrationService.mapper.PersonMapper;
import com.example.SpringSecurityRegistrationService.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public PersonDTO personToPersonDTO(Person person) {
        return PersonMapper.INSTANCE.personToPersonDTO(person);
    }

    @Override
    public Person personDTOToPerson(PersonDTO personDTO) {
        return PersonMapper.INSTANCE.personDTOToPerson(personDTO);
    }

}
