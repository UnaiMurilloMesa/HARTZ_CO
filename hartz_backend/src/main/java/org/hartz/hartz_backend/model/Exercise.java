package org.hartz.hartz_backend.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "exercise")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {

    public enum DifficultyLevel { INTERMEDIATE, ADVANCED, BEGINNER }
    public enum GripType { PRONATED, NA, NEUTRAL } // TODO: completar
    public enum BodyRegion { LOWER, UPPER, FULLBODY } //TODO: completar
    public enum Equipment { BARBELL, PLYO_BOX, PULLUP_BAR, SMITH_MACHINE, DUMBBELLS, ROWING_MACHINE, MACHINE } // TODO: completar
    public enum MuscleGroup { QUADRICEPS, GLUTES, HAMSTIRNGS, LOWER_BACK, PECTORALIS_MAJOR, TRICPES, ANTERIOR_DELTOID,
        LATS, MIDDLE_TRAPEZIUS, BICEPS, RHOMBOIDS, POSTERIOR_DELTOID, LATERAL_DELTOID, LOWER_TRAPEZIUS, CALVES } // TODO: completar

    @Id
    @Column(name = "exercise_name")
    private String exerciseName;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty_level", nullable = false)
    private DifficultyLevel difficultyLevel;

    @ElementCollection(targetClass = MuscleGroup.class)
    @CollectionTable(name = "exercise_muscle_group", joinColumns = @JoinColumn(name = "exercise_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "muscle_group")
    private List<MuscleGroup> muscleGroup;

    @Enumerated(EnumType.STRING)
    @Column(name = "equipment", nullable = false)
    private Equipment equipment;

    @Column(name = "unilateral", nullable = false)
    private boolean unilateral;

    @Enumerated(EnumType.STRING)
    @Column(name = "grip_type")
    private GripType gripType;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_region")
    private BodyRegion bodyRegion;
}
