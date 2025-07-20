package org.hartz.hartz_backend.user;

import org.hartz.hartz_backend.auth.dto.RegisterRequestDTO;
import org.hartz.hartz_backend.plan.PlanType;
import org.hartz.hartz_backend.user.dto.UserDTO;
import org.hartz.hartz_backend.user.entity.UserEntity;
import org.hartz.hartz_backend.user.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

public class UserMapper {

    public static User toDomainUser(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getPlanType(),
                userEntity.getMascot(),
                userEntity.getHeight(),
                userEntity.getWeight(),
                userEntity.getAge(),
                userEntity.getProfilePicture(),
                userEntity.getCreatedAt()
        );
    }

    public static User toDomainUser(RegisterRequestDTO registerRequestDTO, PasswordEncoder passwordEncoder) {
        return new User(
                null, // ID will be set by the database
                registerRequestDTO.getEmail(),
                registerRequestDTO.getUsername(),
                passwordEncoder.encode(registerRequestDTO.getPassword()),
                PlanType.BASIC,
                registerRequestDTO.getMascot(),
                null,
                null,
                null,
                null,
                LocalDateTime.now()
        );
    }

    public static User toDomainUser(UserDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getEmail(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                PlanType.BASIC,
                userDTO.getMascot(),
                null,
                null,
                null,
                null,
                LocalDateTime.now()
        );
    }

    public static UserEntity toUserEntity(User user) {
        return new UserEntity(
                user.id(),
                user.email(),
                user.username(),
                user.password(),
                user.planType(),
                user.mascot(),
                user.height(),
                user.weight(),
                user.age(),
                user.profilePicture(),
                user.createdAt()
        );
    }

    public static UserEntity toUserEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .email(userDTO.getEmail())
                .username(userDTO.getUsername())
                .planType(PlanType.BASIC)
                .mascot(userDTO.getMascot())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static UserEntity toUserEntity(RegisterRequestDTO registerRequestDTO, PasswordEncoder passwordEncoder) {
        return UserEntity.builder()
                .email(registerRequestDTO.getEmail())
                .username(registerRequestDTO.getUsername())
                .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
                .planType(PlanType.BASIC)
                .mascot(registerRequestDTO.getMascot())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static UserDTO toUserDTO(RegisterRequestDTO registerRequestDTO, PasswordEncoder passwordEncoder) {
        return new UserDTO(
                null, // ID will be set by the database
                registerRequestDTO.getUsername(),
                registerRequestDTO.getEmail(),
                passwordEncoder.encode(registerRequestDTO.getPassword()),
                registerRequestDTO.getMascot()
        );
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.id(),
                user.username(),
                user.email(),
                user.password(),
                user.mascot()
        );
    }


}
