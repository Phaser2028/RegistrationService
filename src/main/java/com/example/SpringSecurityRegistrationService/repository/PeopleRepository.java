package com.example.SpringSecurityRegistrationService.repository;

import com.example.SpringSecurityRegistrationService.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface PeopleRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByEmail(String email);

    Optional<Person> findByActivationCode(String code);
}
