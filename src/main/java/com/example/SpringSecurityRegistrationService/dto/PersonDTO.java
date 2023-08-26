package com.example.SpringSecurityRegistrationService.dto;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table
@Data
public class PersonDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @NotBlank(message = "Это поле не должно быть пустым")
    private String username;

    @Column(name = "email")
    @Email(message = "Email должен быть валидным")
    @NotBlank(message = "Это поле не должно быть пустым")
    private String email;

    @Column(name = "activation_code")
    private String activationCode;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "role")
    private String role;

    @Column(name = "profile_pic_dir")
    private String profilePicDir;

    @Column(name = "password")
    @NotBlank(message = "Это поле не должно быть пустым")
    private String password;

    @Transient
    private String confirmPassword;
}
