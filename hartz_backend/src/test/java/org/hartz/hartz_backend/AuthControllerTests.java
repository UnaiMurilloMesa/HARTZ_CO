package org.hartz.hartz_backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hartz.hartz_backend.common.enums.PlanType;
import org.hartz.hartz_backend.common.exception.PasswordDoesNotMatchEmailException;
import org.hartz.hartz_backend.model.User;
import org.hartz.hartz_backend.model.dto.LoginRequestDTO;
import org.hartz.hartz_backend.model.dto.RegisterRequestDTO;
import org.hartz.hartz_backend.persistence.postgres.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Nested
    @DisplayName("Register Tests")
    class RegisterTests {

        @Test
        void shouldRegisterUserSuccessfully() throws Exception {
            RegisterRequestDTO request = new RegisterRequestDTO(
                    "newuser@example.com",
                    "securePass123",
                    "newuser",
                    "bear"
            );

            mockMvc.perform(post("/api/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.token").isNotEmpty());
        }

        @Test
        void shouldFailWhenEmailAlreadyExists() throws Exception {
            userRepository.save(
                    User.builder()
                            .email("existing@example.com")
                            .username("someuser")
                            .password("hashedpass")
                            .planType(PlanType.BASIC)
                            .mascot("bear")
                            .build()
            );

            RegisterRequestDTO request = new RegisterRequestDTO(
                    "existing@example.com",
                    "securePass123",
                    "newuser",
                    "bear"
            );

            mockMvc.perform(post("/api/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("Email taken"));
        }

        @Test
        void shouldFailWhenUsernameAlreadyExists() throws Exception {
            userRepository.save(
                    User.builder()
                            .email("unique@example.com")
                            .username("duplicateuser")
                            .password("hashedpass")
                            .planType(PlanType.BASIC)
                            .mascot("bear")
                            .build()
            );

            RegisterRequestDTO request = new RegisterRequestDTO(
                    "another@example.com",
                    "securePass123",
                    "duplicateuser",
                    "bear"
            );

            mockMvc.perform(post("/api/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("Username taken"));
        }

        @Test
        void shouldFailWhenPasswordIsTooShort() throws Exception {
            RegisterRequestDTO request = new RegisterRequestDTO(
                    "shortpass@example.com",
                    "123",
                    "user123",
                    "bear"
            );

            mockMvc.perform(post("/api/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("Password shorter than 6 characters"));
        }

        @Test
        void shouldFailWhenEmailFormatIsInvalid1() throws Exception {
            RegisterRequestDTO request = new RegisterRequestDTO(
                    "invalid-email.com",
                    "securePass123",
                    "userinvalidemail",
                    "bear"
            );

            mockMvc.perform(post("/api/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("Email format exception"));
        }
        @Test
        void shouldFailWhenEmailFormatIsInvalid2() throws Exception {
            RegisterRequestDTO request = new RegisterRequestDTO(
                    "invalid@emailcom",
                    "securePass123",
                    "userinvalidemail",
                    "bear"
            );

            mockMvc.perform(post("/api/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("Email format exception"));

        }
    }

    @Nested
    @DisplayName("Login Tests")
    class LoginTests {

        @BeforeEach
        void insertUser() throws Exception {
            RegisterRequestDTO request = new RegisterRequestDTO(
                    "loginuser@example.com",
                    "securePass123",
                    "user",
                    "bear"
            );
            mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.token").isNotEmpty());

        }

        @Test
        void shouldLoginSuccessfully() throws Exception {
            LoginRequestDTO request = new LoginRequestDTO(
                    "loginuser@example.com",
                    "securePass123"
            );

            mockMvc.perform(post("/api/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.token").isNotEmpty());
        }

        @Test
        void shouldFailLoginWithWrongPassword() throws Exception {
            LoginRequestDTO request = new LoginRequestDTO(
                    "loginuser@example.com",
                    "wrongpassword"
            );

            mockMvc.perform(post("/api/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("Password does not match with email"));
        }

        @Test
        void shouldFailLoginWithNonexistentEmail() throws Exception {
            LoginRequestDTO request = new LoginRequestDTO(
                    "notfound@example.com",
                    "securePass123"
            );

            mockMvc.perform(post("/api/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("Email not registered"));
        }
    }
}
