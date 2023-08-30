package com.example.SpringSecurityRegistrationService.controller;


import com.example.SpringSecurityRegistrationService.dto.PersonDTO;
import com.example.SpringSecurityRegistrationService.mapper.PersonMapper;
import com.example.SpringSecurityRegistrationService.service.RegistrationService;
import com.example.SpringSecurityRegistrationService.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final PersonValidator personValidator;

    private final PersonMapper personMapper;


    @Autowired
    public AuthController(RegistrationService registrationService,
                          PersonValidator personValidator,
                          PersonMapper personMapper) {

        this.registrationService = registrationService;
        this.personValidator = personValidator;
        this.personMapper = personMapper;
    }


    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }


    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("personDTO") PersonDTO personDTO) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("personDTO") @Valid PersonDTO personDTO,
                                      BindingResult bindingResult,
                                      @RequestParam("image") MultipartFile file) {

        personValidator.validate(personDTO, bindingResult);

        if (bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(personMapper.personDTOToPerson(personDTO), file);

        return "redirect:/auth/login";
    }


    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model) {

        Boolean isActivate = registrationService.activateUser(code);

        if (isActivate) {
            model.addAttribute("message", "Учётная запись активирована.");
        }else{
            model.addAttribute("message", "Что-то пошло не так.");
        }

        return "/auth/login";
    }


}