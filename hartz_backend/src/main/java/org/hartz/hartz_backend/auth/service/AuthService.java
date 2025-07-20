package org.hartz.hartz_backend.auth.service;

import lombok.RequiredArgsConstructor;
import org.hartz.hartz_backend.auth.dto.AuthResponseDTO;
import org.hartz.hartz_backend.auth.dto.LoginRequestDTO;
import org.hartz.hartz_backend.auth.dto.RegisterRequestDTO;
import org.hartz.hartz_backend.auth.jwt.JwtService;
import org.hartz.hartz_backend.user.UserMapper;
import org.hartz.hartz_backend.user.model.User;
import org.hartz.hartz_backend.user.persistence.JpaUserRepositoryAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final JpaUserRepositoryAdapter jpaUserRepositoryAdapter;

    public AuthResponseDTO register(RegisterRequestDTO requestDTO) {
        User domainUser = UserMapper.toDomainUser(requestDTO, passwordEncoder);
        User savedUser = jpaUserRepositoryAdapter.save(domainUser)
                .orElseThrow(() -> new RuntimeException("Failed to save user"));
        return new AuthResponseDTO(jwtService.generateToken(savedUser.email()));
    }

    public AuthResponseDTO login(LoginRequestDTO requestDTO) {
        User user = jpaUserRepositoryAdapter.findByEmail(requestDTO.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(requestDTO.getPassword(), user.password())) {
            throw new RuntimeException("Wrong credentials");
        }

        return new AuthResponseDTO(jwtService.generateToken(user.email()));
    }
}
