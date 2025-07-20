package org.hartz.hartz_backend.user.controller;

import lombok.RequiredArgsConstructor;
import org.hartz.hartz_backend.user.UserMapper;
import org.hartz.hartz_backend.user.dto.UserDTO;
import org.hartz.hartz_backend.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String id) {
        return userService.findById(id)
                .map(UserMapper::toUserDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado: " + id));
    }
    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
                .map(UserMapper::toUserDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado" + email));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(UserMapper::toUserDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with username: " + username));
    }

//    @PostMapping
//    public ResponseEntity<User> save(@RequestBody UserDTO user) {
//        Optional<User> created = userService.save(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(created.get());
//    }

//    @GetMapping("/exists/email/{email}")
//    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
//        return ResponseEntity.ok(userService.existsByEmail(email));
//    }

//    @GetMapping("/exists/username/{username}")
//    public ResponseEntity<Boolean> existsByUsername(@PathVariable String username) {
//        return ResponseEntity.ok(userService.existsByUsername(username));
//    }

}
