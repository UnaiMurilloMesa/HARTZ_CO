package org.hartz.hartz_backend.user.controller;

import lombok.RequiredArgsConstructor;
import org.hartz.hartz_backend.user.dto.UserDTO;
import org.hartz.hartz_backend.user.persistence.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    // TODO Controlar errores (devolviendo forbidden cuando el email no existe)
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return userRepository.findByEmail(email)
                .map(UserDTO::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + email));
    }

    // TODO Controlar errores (devolviendo forbidden cuando el username no existe)
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username)
                .map(UserDTO::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with username: " + username));
    }

}
