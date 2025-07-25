package org.hartz.hartz_backend.model.dto;

import lombok.Data;
import org.hartz.hartz_backend.model.User;

import java.time.LocalDateTime;

@Data
public class PersonalPrivateInfoDTO {
    private String username;
    private String mascot;
    private String biography;
    private Double height;
    private Double weight;
    private LocalDateTime createdAt;

    private PersonalPrivateInfoDTO(String username,
                                  String mascot,
                                  Double height,
                                  Double weight,
                                  LocalDateTime createdAt) {
        this.username = username;
        this.mascot = mascot;
        this.height = height;
        this.weight = weight;
        this.createdAt = createdAt;
    }

    public static PersonalPrivateInfoDTO toDto(User user) {
        return new PersonalPrivateInfoDTO(user.getUsername(),
                user.getMascot(),
                user.getHeight(),
                user.getWeight(),
                user.getCreatedAt());
    }
}
