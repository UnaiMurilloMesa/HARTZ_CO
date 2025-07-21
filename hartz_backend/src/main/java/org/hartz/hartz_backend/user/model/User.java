package org.hartz.hartz_backend.user.model;

import org.hartz.hartz_backend.common.enums.PlanType;

import java.time.LocalDateTime;

public record User(
        Long id,
        String email,
        String username,
        String password,
        PlanType planType,
        String mascot,
        Double height,
        Double weight,
        Integer age,
        String profilePicture,
        LocalDateTime createdAt) {
}
