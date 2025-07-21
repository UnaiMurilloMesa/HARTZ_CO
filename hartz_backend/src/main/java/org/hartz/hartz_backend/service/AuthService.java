package org.hartz.hartz_backend.service;

import lombok.RequiredArgsConstructor;
import org.hartz.hartz_backend.common.exception.EmailTakenException;
import org.hartz.hartz_backend.common.exception.NotCorrectEmailFormatException;
import org.hartz.hartz_backend.common.exception.PasswordTooShortException;
import org.hartz.hartz_backend.model.dto.AuthResponseDTO;
import org.hartz.hartz_backend.model.dto.LoginRequestDTO;
import org.hartz.hartz_backend.model.dto.RegisterRequestDTO;
import org.hartz.hartz_backend.common.enums.PlanType;
import org.hartz.hartz_backend.common.exception.UsernameTakenException;
import org.hartz.hartz_backend.model.User;
import org.hartz.hartz_backend.persistence.postgres.UserRepository;
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
        if (userRepository.existsByUsername(requestDTO.getUsername())) {
            throw new UsernameTakenException();
        } else if (userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new EmailTakenException();
        } else if (requestDTO.getPassword().length() < 6) {
            throw new PasswordTooShortException();
        } else if (!isEmailValid(requestDTO.getEmail())) {
            throw new NotCorrectEmailFormatException();
        } else {
            User user = User.builder()
                    .email(requestDTO.getEmail())
                    .username(requestDTO.getUsername())
                    .password(passwordEncoder.encode(requestDTO.getPassword()))
                    .planType(PlanType.BASIC)
                    .mascot(requestDTO.getMascot())
                    .createdAt(LocalDateTime.now())
                    .build();

            userRepository.save(user);
            return new AuthResponseDTO(jwtService.generateToken(user.getUsername()));
        }
    }

    private boolean isEmailValid(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email != null && email.matches(regex);
    }

    // TODO: Controlar errores para que no devuelva forbidden siempre
    public AuthResponseDTO login(LoginRequestDTO requestDTO) {
        User user = userRepository.findByEmail(requestDTO.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong credentials");
        }

        return new AuthResponseDTO(jwtService.generateToken(user.getUsername()));
    }
}
