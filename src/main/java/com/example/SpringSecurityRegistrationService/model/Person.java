package com.example.SpringSecurityRegistrationService.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String phone_number;

    @Column
    private String role;

    @Column
    private String password;

    @Column(name = "profile_pic_dir")
    private String profilePicDir;

    @Column(name = "activation_code")
    private String activationCode;
}
