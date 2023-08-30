package com.example.SpringSecurityRegistrationService;

import com.example.SpringSecurityRegistrationService.controller.AuthController;
import com.example.SpringSecurityRegistrationService.dto.PersonDTO;
import com.example.SpringSecurityRegistrationService.mapper.PersonMapper;
import com.example.SpringSecurityRegistrationService.service.RegistrationService;
import com.example.SpringSecurityRegistrationService.service.impl.RegistrationServiceImpl;
import com.example.SpringSecurityRegistrationService.util.PersonValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationServiceImpl registrationService;

    @MockBean
    private PersonMapper personMapper;

    @MockBean
    private PersonValidator personValidator;

    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/auth/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("auth/login"));
    }

    @Test
    public void testRegistrationPage() throws Exception {
        mockMvc.perform(get("/auth/registration"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Registration page"));
    }


}