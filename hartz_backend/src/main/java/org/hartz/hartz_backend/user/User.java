package org.hartz.hartz_backend.user;

import jakarta.persistence.*;
import lombok.*;
import org.hartz.hartz_backend.plan.PlanType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "plan_type", nullable = false)
    private PlanType planType;

    private String mascot;

    private Double height;
    private Double weight;
    private Integer age;
    private String profilePicture;

    private LocalDateTime createdAt;

}
