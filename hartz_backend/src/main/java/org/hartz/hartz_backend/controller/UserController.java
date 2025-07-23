package org.hartz.hartz_backend.controller;

import lombok.RequiredArgsConstructor;
import org.hartz.hartz_backend.model.User;
import org.hartz.hartz_backend.model.dto.UserInfoDTO;
import org.hartz.hartz_backend.persistence.postgres.UserRepositoryAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepositoryAdapter userRepository;

    @GetMapping("/email/{email}")
    public ResponseEntity<UserInfoDTO> getUserByEmail(@PathVariable String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UserInfoDTO.toDTO(userOptional.get()));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserInfoDTO> getUserByUsername(@PathVariable String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UserInfoDTO.toDTO(userOptional.get()));
    }
}
