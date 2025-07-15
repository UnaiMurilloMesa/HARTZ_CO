package org.hartz.hartz_backend.auth.service;

import lombok.RequiredArgsConstructor;
import org.hartz.hartz_backend.auth.dto.AuthResponseDTO;
import org.hartz.hartz_backend.auth.dto.LoginRequestDTO;
import org.hartz.hartz_backend.auth.dto.RegisterRequestDTO;
import org.hartz.hartz_backend.auth.jwt.JwtService;
import org.hartz.hartz_backend.plan.PlanType;
import org.hartz.hartz_backend.user.User;
import org.hartz.hartz_backend.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponseDTO register(RegisterRequestDTO requestDTO) {
        User user = User.builder()
                .email(requestDTO.getEmail())
                .username(requestDTO.getUsername())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .planType(PlanType.BASIC)
                .mascot(requestDTO.getMascot())
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
        return new AuthResponseDTO(jwtService.generateToken(user.getEmail()));
    }

    public AuthResponseDTO login(LoginRequestDTO requestDTO) {
        User user = userRepository.findByEmail(requestDTO.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong credentials");
        }

        return new AuthResponseDTO(jwtService.generateToken(user.getEmail()));
    }
}
