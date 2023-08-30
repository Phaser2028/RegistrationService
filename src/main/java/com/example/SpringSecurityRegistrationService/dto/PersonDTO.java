package com.example.SpringSecurityRegistrationService.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PersonDTO {

    @NotEmpty(message = "Это поле не должно быть пустым")
    private String username;

    @Email(message = "Email должен быть валидным")
    @NotEmpty(message = "Это поле не должно быть пустым")
    private String email;

    private String activationCode;

    @NotEmpty(message = "Это поле не должно быть пустым")
    private String password;

    private String confirmPassword;

}
