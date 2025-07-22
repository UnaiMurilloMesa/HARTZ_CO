package org.hartz.hartz_backend.controller;

import lombok.RequiredArgsConstructor;
import org.hartz.hartz_backend.model.dto.UserInfoDTO;
import org.hartz.hartz_backend.persistence.postgres.UserRepository;
import org.hartz.hartz_backend.persistence.postgres.UserRepositoryAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepositoryAdapter userRepository;

    // TODO Controlar errores (devolviendo forbidden cuando el email no existe)
    @GetMapping("/email/{email}")
    public ResponseEntity<UserInfoDTO> getUserByEmail(@PathVariable String email) {
        return userRepository.findByEmail(email)
                .map(UserInfoDTO::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + email));
    }

    // TODO Controlar errores (devolviendo forbidden cuando el username no existe)
    @GetMapping("/username/{username}")
    public ResponseEntity<UserInfoDTO> getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username)
                .map(UserInfoDTO::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with username: " + username));
    }

}
