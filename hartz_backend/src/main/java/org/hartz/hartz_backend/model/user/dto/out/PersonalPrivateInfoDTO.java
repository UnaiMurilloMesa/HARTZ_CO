package org.hartz.hartz_backend.model.user.dto.out;

import lombok.Data;
import org.hartz.hartz_backend.model.user.User;

import java.time.LocalDateTime;

@Data
public class PersonalPrivateInfoDTO {
    private String username;
    private String email;
    private String mascot;
    private String biography;
    private Double height;
    private Double weight;
    private LocalDateTime createdAt;

    private PersonalPrivateInfoDTO(String username,
                                  String email,
                                  String mascot,
                                  Double height,
                                  Double weight,
                                  String biography,
                                  LocalDateTime createdAt) {
        this.username = username;
        this.email = email;
        this.mascot = mascot;
        this.height = height;
        this.biography = biography;
        this.weight = weight;
        this.createdAt = createdAt;
    }

    public static PersonalPrivateInfoDTO toDto(User user) {
        return new PersonalPrivateInfoDTO(
                user.getUsername(),
                user.getEmail(),
                user.getMascot(),
                user.getHeight(),
                user.getWeight(),
                user.getBiography(),
                user.getCreatedAt());
    }
}
