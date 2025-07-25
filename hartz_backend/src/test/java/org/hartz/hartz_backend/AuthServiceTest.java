package org.hartz.hartz_backend;

import org.hartz.hartz_backend.exception.userExceptions.EmailNotFoundException;
import org.hartz.hartz_backend.exception.userExceptions.EmailTakenException;
import org.hartz.hartz_backend.exception.userExceptions.NotCorrectEmailFormatException;
import org.hartz.hartz_backend.exception.userExceptions.PasswordDoesNotMatchEmailException;
import org.hartz.hartz_backend.exception.userExceptions.PasswordTooShortException;
import org.hartz.hartz_backend.exception.userExceptions.UsernameTakenException;
import org.hartz.hartz_backend.model.user.User;
import org.hartz.hartz_backend.model.user.dto.out.AuthResponseDTO;
import org.hartz.hartz_backend.model.user.dto.in.LoginRequestDTO;
import org.hartz.hartz_backend.model.user.dto.in.RegisterRequestDTO;
import org.hartz.hartz_backend.persistence.postgre.UserRepositoryAdapter;
import org.hartz.hartz_backend.service.AuthService;
import org.hartz.hartz_backend.service.CustomUserDetailsService;
import org.hartz.hartz_backend.service.JwtService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepositoryAdapter userRepositoryAdapter;

    @InjectMocks // Injecta los mocks como dependencias
    private AuthService authService;


    String username = "newuser";
    String password = "securepassword";
    String email = "newuser@example.com";
    String token = "token";
    User mockUser = User.builder()
            .username(username)
            .password(password)
            .email(email)
            .build();

    @Nested
    @DisplayName("Register Tests")
    class RegisterTests {

        @Test
        void shouldRegisterUserSuccessfully() throws Exception {

            RegisterRequestDTO request = new RegisterRequestDTO(
                    email,
                    "securePass123",
                    username,
                    "bear"
            );

            when(userRepositoryAdapter.existsByUsername(username)).thenReturn(false);
            when(userRepositoryAdapter.existsByEmail(email)).thenReturn(false);
            when(jwtService.generateToken(username)).thenReturn(token);

            AuthResponseDTO response = authService.register(request);
            assertEquals(response.getToken(), token);
        }

        @Test
        void shouldFailWhenEmailAlreadyExists() throws Exception {
            RegisterRequestDTO request = new RegisterRequestDTO(
                    email,
                    "securePass123",
                    username,
                    "bear"
            );

            when(userRepositoryAdapter.existsByUsername(username)).thenReturn(false);
            when(userRepositoryAdapter.existsByEmail(email)).thenReturn(true);

            try {
                AuthResponseDTO response = authService.register(request);
                fail("Should have fail for existing email");
            } catch (EmailTakenException ex) {

            } catch (Exception e) {
                fail("Should have thown EmailTakenException");
            }
        }

        @Test
        void shouldFailWhenUsernameAlreadyExists() throws Exception {
            RegisterRequestDTO request = new RegisterRequestDTO(
                    email,
                    "securePass123",
                    username,
                    "bear"
            );

            when(userRepositoryAdapter.existsByUsername(username)).thenReturn(true);

            try {
                AuthResponseDTO response = authService.register(request);
                fail("Should have fail for existing username");
            } catch (UsernameTakenException ex) {

            } catch (Exception e) {
                fail("Should have thown UsernameTakenException");
            }
        }

        @Test
        void shouldFailWhenPasswordIsTooShort() throws Exception {
            RegisterRequestDTO request = new RegisterRequestDTO(
                    email,
                    "123",
                    username,
                    "bear"
            );

            when(userRepositoryAdapter.existsByUsername(username)).thenReturn(false);
            when(userRepositoryAdapter.existsByEmail(email)).thenReturn(false);

            try {
                AuthResponseDTO response = authService.register(request);
                fail("Should have fail for short password");
            } catch (PasswordTooShortException ex) {

            } catch (Exception e) {
                fail("Should have thown PasswordTooShortException");
            }
        }

        @Test
        void shouldFailWhenEmailFormatIsInvalid1() throws Exception {
            String invalidEmail = "invalid-email.com";
            RegisterRequestDTO request = new RegisterRequestDTO(
                    invalidEmail,
                    "securePass123",
                    username,
                    "bear"
            );

            when(userRepositoryAdapter.existsByUsername(username)).thenReturn(false);
            when(userRepositoryAdapter.existsByEmail(invalidEmail)).thenReturn(false);

            try {
                AuthResponseDTO response = authService.register(request);
                fail("Should have fail for invalid email");
            } catch (NotCorrectEmailFormatException ex) {

            } catch (Exception e) {
                fail("Should have thown NoCorrectEmailFormatException");
            }
        }


        @Test
        void shouldFailWhenEmailFormatIsInvalid2() throws Exception {
            String invalidEmail = "invalid@emailcom";
            RegisterRequestDTO request = new RegisterRequestDTO(
                    invalidEmail,
                    "securePass123",
                    username,
                    "bear"
            );

            when(userRepositoryAdapter.existsByUsername(username)).thenReturn(false);
            when(userRepositoryAdapter.existsByEmail(invalidEmail)).thenReturn(false);

            try {
                AuthResponseDTO response = authService.register(request);
                fail("Should have fail for invalid email");
            } catch (NotCorrectEmailFormatException ex) {

            } catch (Exception e) {
                fail("Should have thown NoCorrectEmailFormatException");
            }
        }
    }

    @Nested
    @DisplayName("Login Tests")
    class LoginTests {

        @Test
        void shouldLoginSuccessfully() throws Exception {
            LoginRequestDTO request = new LoginRequestDTO(
                    email,
                    password
            );

            when(jwtService.generateToken(username)).thenReturn(token);
            when(userRepositoryAdapter.existsByEmail(email)).thenReturn(true);
            when(userRepositoryAdapter.findByEmail(email)).thenReturn(Optional.of(mockUser));
            when(passwordEncoder.matches(any(), anyString())).thenReturn(true);

            AuthResponseDTO response = authService.login(request);
            assertEquals(token, response.getToken());
        }

        @Test
        void shouldFailLoginWithWrongPassword() throws Exception {
            LoginRequestDTO request = new LoginRequestDTO(
                    email,
                    password
            );

            when(userRepositoryAdapter.existsByEmail(email)).thenReturn(true);
            when(userRepositoryAdapter.findByEmail(email)).thenReturn(Optional.of(mockUser));
            when(passwordEncoder.matches(any(), anyString())).thenReturn(false);

            try {
                AuthResponseDTO response = authService.login(request);
                fail("Should have thrown PasswordDoesNotMatchEmailException");
            } catch (PasswordDoesNotMatchEmailException e) {

            } catch (Exception e) {
                fail("Should have thrown PasswordDoesNotMatchEmailException");
            }
        }

        @Test
        void shouldFailLoginWithNonexistentEmail() throws Exception {
            LoginRequestDTO request = new LoginRequestDTO(
                    email,
                    password
            );

            when(userRepositoryAdapter.existsByEmail(email)).thenReturn(false);

            try {
                AuthResponseDTO response = authService.login(request);
                fail("Should have thrown EmailNotFoundException");
            } catch (EmailNotFoundException e) {

            } catch (Exception e) {
                fail("Should have thrown EmailNotFoundException");
            }
        }
    }
}
