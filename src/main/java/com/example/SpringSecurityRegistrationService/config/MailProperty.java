package com.example.SpringSecurityRegistrationService.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperty {

    private Integer port;
    private String username;
    private String password;
    private String host;
    private String protocol;

}
