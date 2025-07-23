package org.hartz.hartz_backend.controller;

import lombok.RequiredArgsConstructor;
import org.hartz.hartz_backend.exception.userExceptions.EmailNotFoundException;
import org.hartz.hartz_backend.exception.userExceptions.EmailTakenException;
import org.hartz.hartz_backend.exception.userExceptions.NotCorrectEmailFormatException;
import org.hartz.hartz_backend.exception.userExceptions.PasswordDoesNotMatchEmailException;
import org.hartz.hartz_backend.exception.userExceptions.PasswordTooShortException;
import org.hartz.hartz_backend.exception.userExceptions.UsernameTakenException;
import org.hartz.hartz_backend.model.dto.AuthResponseDTO;
import org.hartz.hartz_backend.model.dto.LoginRequestDTO;
import org.hartz.hartz_backend.model.dto.RegisterRequestDTO;
import org.hartz.hartz_backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Object> register (@RequestBody RegisterRequestDTO requestDTO) {
        try {
            AuthResponseDTO responseDTO = authService.register(requestDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (UsernameTakenException e) {
            return ResponseEntity.badRequest().body("Username taken");
        } catch (EmailTakenException e) {
            return ResponseEntity.badRequest().body("Email taken");
        } catch (PasswordTooShortException e) {
            return ResponseEntity.badRequest().body("Password shorter than 6 characters");
        } catch (NotCorrectEmailFormatException e) {
            return ResponseEntity.badRequest().body("Email format exception");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login (@RequestBody LoginRequestDTO requestDTO) {
        try {
            AuthResponseDTO responseDTO = authService.login(requestDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (EmailNotFoundException e) {
            return ResponseEntity.badRequest().body("Email not registered");
        } catch (PasswordDoesNotMatchEmailException e) {
            return ResponseEntity.badRequest().body("Password does not match with email");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
