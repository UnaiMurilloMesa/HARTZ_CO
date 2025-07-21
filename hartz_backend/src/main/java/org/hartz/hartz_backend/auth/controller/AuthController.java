package org.hartz.hartz_backend.auth.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.hartz.hartz_backend.auth.dto.AuthResponseDTO;
import org.hartz.hartz_backend.auth.dto.LoginRequestDTO;
import org.hartz.hartz_backend.auth.dto.RegisterRequestDTO;
import org.hartz.hartz_backend.auth.service.AuthService;
import org.hartz.hartz_backend.common.exception.UsernameTakenException;
import org.hartz.hartz_backend.user.entity.User;
import org.hibernate.Internal;
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
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login (@RequestBody LoginRequestDTO requestDTO) {
        return ResponseEntity.ok(authService.login(requestDTO));
    }
}
