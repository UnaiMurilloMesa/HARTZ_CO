package org.hartz.hartz_backend.user.service;

import lombok.RequiredArgsConstructor;
import org.hartz.hartz_backend.user.UserMapper;
import org.hartz.hartz_backend.user.dto.UserDTO;
import org.hartz.hartz_backend.user.model.User;
import org.hartz.hartz_backend.user.port.in.CreateUserUseCase;
import org.hartz.hartz_backend.user.port.in.GetUserUseCase;
import org.hartz.hartz_backend.user.port.out.UserRepositoryOutPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements CreateUserUseCase, GetUserUseCase {
    private final UserRepositoryOutPort userRepositoryOutPort;

    public Optional<User> save(UserDTO userDTO) {
        User user = UserMapper.toDomainUser(userDTO);
        return Optional.of(userRepositoryOutPort.save(user).orElseThrow(() -> new RuntimeException("Failed to save user")));
    }

    @Override
    public Optional<User> findById(String userId) {
        User user = userRepositoryOutPort.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + userId));
        return Optional.of(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = userRepositoryOutPort.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return Optional.of(user);
    }

    public Optional<User> findByUsername(String username) {
        return Optional.of(userRepositoryOutPort.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username)));
    }

    public boolean existsByEmail(String email) {
        return userRepositoryOutPort.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepositoryOutPort.existsByUsername(username);
    }

}
