package org.hartz.hartz_backend.auth.service;

import lombok.RequiredArgsConstructor;
import org.hartz.hartz_backend.auth.dto.AuthResponseDTO;
import org.hartz.hartz_backend.auth.dto.LoginRequestDTO;
import org.hartz.hartz_backend.auth.dto.RegisterRequestDTO;
import org.hartz.hartz_backend.common.enums.PlanType;
import org.hartz.hartz_backend.user.entity.User;
import org.hartz.hartz_backend.user.persistence.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthResponseDTO register(RegisterRequestDTO requestDTO) {
        User domainUser = User.builder()
                .email(requestDTO.getEmail())
                .username(requestDTO.getUsername())
                .password(requestDTO.getPassword())
                .mascot(requestDTO.getMascot())
                .planType(PlanType.BASIC)
                .build();

        User savedUser = userRepository.save(domainUser);
        return new AuthResponseDTO(jwtService.generateToken(savedUser.getEmail()));
    }

    public AuthResponseDTO login(LoginRequestDTO requestDTO) {
        User user = userRepository.findByEmail(requestDTO.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong credentials");
        }

        return new AuthResponseDTO(jwtService.generateToken(user.getEmail()));
    }
}
