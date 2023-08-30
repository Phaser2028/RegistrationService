package com.example.SpringSecurityRegistrationService;

import com.example.SpringSecurityRegistrationService.dto.PersonDTO;
import com.example.SpringSecurityRegistrationService.repository.PeopleRepository;
import com.example.SpringSecurityRegistrationService.util.PersonValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class PersonValidatorTest {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final PeopleRepository peopleRepository = mock(PeopleRepository.class);

    private final PersonValidator personValidator = new PersonValidator(passwordEncoder, peopleRepository);

    @Test
    public void testValidPerson() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail("test@example.com");
        personDTO.setPassword("password");
        personDTO.setConfirmPassword("password");

        Errors errors = new BeanPropertyBindingResult(personDTO, "personDTO");
        personValidator.validate(personDTO, errors);

        assertEquals(0, errors.getErrorCount());
    }



}