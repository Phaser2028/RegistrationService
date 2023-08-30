package com.example.SpringSecurityRegistrationService.controller;


import com.example.SpringSecurityRegistrationService.model.Person;
import com.example.SpringSecurityRegistrationService.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HelloController {
    private final PeopleService peopleService;

    public HelloController(@Autowired PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/hello")
    public String sayHello(Model model, Principal principal) {

        Person person = peopleService.loadUserByUsername(principal.getName()).getPerson();

        model.addAttribute("person", person);
        return "hello";
    }

}
