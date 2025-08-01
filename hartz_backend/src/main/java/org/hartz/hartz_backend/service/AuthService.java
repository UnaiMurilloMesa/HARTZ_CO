package org.hartz.hartz_backend.service;

import lombok.RequiredArgsConstructor;
import org.hartz.hartz_backend.exception.userExceptions.EmailNotFoundException;
import org.hartz.hartz_backend.exception.userExceptions.EmailTakenException;
import org.hartz.hartz_backend.exception.userExceptions.NotCorrectEmailFormatException;
import org.hartz.hartz_backend.exception.userExceptions.PasswordDoesNotMatchEmailException;
import org.hartz.hartz_backend.exception.userExceptions.PasswordTooShortException;
import org.hartz.hartz_backend.exception.userExceptions.UsernameTakenException;
import org.hartz.hartz_backend.model.user.User;
import org.hartz.hartz_backend.model.user.dto.in.LoginRequestDTO;
import org.hartz.hartz_backend.model.user.dto.in.RegisterRequestDTO;
import org.hartz.hartz_backend.model.user.dto.out.AuthResponseDTO;
import org.hartz.hartz_backend.persistence.postgre.UserRepositoryAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepositoryAdapter userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final int MIN_PASSWORD_LENGHT = 6;

    public AuthResponseDTO register(RegisterRequestDTO requestDTO) {
        if (userRepository.existsByUsername(requestDTO.getUsername())) {
            throw new UsernameTakenException();
        } else if (userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new EmailTakenException();
        } else if (requestDTO.getPassword().length() < MIN_PASSWORD_LENGHT) {
            throw new PasswordTooShortException();
        } else if (!isEmailValid(requestDTO.getEmail())) {
            throw new NotCorrectEmailFormatException();
        } else {
            User user = User.builder()
                    .email(requestDTO.getEmail())
                    .username(requestDTO.getUsername())
                    .password(passwordEncoder.encode(requestDTO.getPassword()))
                    .planType(User.PlanType.BASIC)
                    .mascot(requestDTO.getMascot())
                    .createdAt(Instant.now())
                    .build();

            userRepository.save(user);
            return new AuthResponseDTO(jwtService.generateToken(user.getUsername()));
        }
    }

    private boolean isEmailValid(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email != null && email.matches(regex);
    }

    public AuthResponseDTO login(LoginRequestDTO requestDTO) {

        if (!userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new EmailNotFoundException();
        }

        Optional<User> user = userRepository.findByEmail(requestDTO.getEmail());

        if (!passwordEncoder.matches(requestDTO.getPassword(), user.get().getPassword())) {
            throw new PasswordDoesNotMatchEmailException();
        }

        return new AuthResponseDTO(jwtService.generateToken(user.get().getUsername()));
    }

}
