package org.hartz.hartz_backend.model.exercise;

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
    public enum GripType { PRONATED, NEUTRAL, SUPINATED, MIXED, SUPINATED_TO_PRONATED, WIDE_PRONATED, CLOSE_PRONATED }
    public enum BodyRegion { LOWER, UPPER, FULLBODY }
    public enum Equipment { BARBELL, PLYO_BOX, PULLUP_BAR, SMITH, DUMBBELL, MACHINE, PLATE,
        ABDOMINAL_WHEEL, CABLE, BAR, BOX, PARALLEL_BARS, Z_BAR, AIR_BIKE, TREADMILL, STATIONARY_BIKE,
        HACK_SQUAT, PENDULUM_SQUAT, LEG_PRESS, BENCH, T_BAR, RESISTANCE_BAND, TRAP_BAR, PEC_DECK }
    public enum MuscleGroup { QUADRICEPS, GLUTES, HAMSTIRNGS, LOWER_BACK, PECTORALIS_MAJOR, TRICPES, ANTERIOR_DELTOID,
        LATS, MIDDLE_TRAPEZIUS, BICEPS, RHOMBOIDS, POSTERIOR_DELTOID, LATERAL_DELTOID, LOWER_TRAPEZIUS, CALVES,
        TRAPEZIUS, DELTOIDS, ABS, ABDUCTORS, ADDUCTORS, FOREARM, FULLBODY, NECK, SHOULDERS, GLUTEUS_MEDIUS,
        HIP_ABDUCTORS, REAR_DELTOIDS, ROTATOR_CUFF, FRONT_DELTOIDS, UPPER_CHEST, CORE, CHEST }
    public enum ExerciseType { CARDIO, WEIGHT_REPS, TIME, BODY_REPS }

    @Id
    @Column(name = "exercise_name")
    private String exerciseName;

    @Enumerated(EnumType.STRING)
    @Column(name = "exercise_type", nullable = false)
    private ExerciseType exerciseType;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty_level", nullable = false)
    private DifficultyLevel difficultyLevel;

    @ElementCollection(targetClass = MuscleGroup.class)
    @CollectionTable(name = "exercise_muscle_group", joinColumns = @JoinColumn(name = "exercise_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "muscle_group", nullable = false)
    private List<MuscleGroup> muscleGroup;

    @Enumerated(EnumType.STRING)
    @Column(name = "equipment", nullable = true)
    private Equipment equipment;

    @Column(name = "unilateral", nullable = false)
    private boolean unilateral;

    @Enumerated(EnumType.STRING)
    @Column(name = "grip_type", nullable = true)
    private GripType gripType;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_region", nullable = false)
    private BodyRegion bodyRegion;
}
