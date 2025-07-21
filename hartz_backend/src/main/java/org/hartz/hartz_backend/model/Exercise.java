package org.hartz.hartz_backend.model;

public class Exercise {

    public enum DifficultyLevel { INTERMEDIATE, ADVANCED, BEGINNER }
    public enum GripType { PRONATED, NA, NEUTRAL } // TODO: completar
    public enum BodyRegion { LOWER, UPPER, FULLBODY } //TODO: completar
    public enum Equipment { BARBELL, PLYO_BOX, PULLUP_BAR, SMITH_MACHINE, DUMBBELLS, ROWING_MACHINE, MACHINE } // TODO: completar
    public enum MuscleGroup { QUADRICEPS, GLUTES, HAMSTIRNGS, LOWER_BACK, PECTORALIS_MAJOR, TRICPES, ANTERIOR_DELTOID,
        LATS, MIDDLE_TRAPEZIUS, BICEPS, RHOMBOIDS, POSTERIOR_DELTOID, LATERAL_DELTOID, LOWER_TRAPEZIUS, CALVES } // TODO: completar

    private String exerciseName;
    private DifficultyLevel difficultyLevel;
    private MuscleGroup[] muscleGroup;
    private Equipment equipment;
    private boolean unilateral;
    private GripType gripType;
    private BodyRegion bodyRegion;
}
