--
-- PostgreSQL database dump
--

-- Dumped from database version 16.9 (Debian 16.9-1.pgdg120+1)
-- Dumped by pg_dump version 16.9 (Debian 16.9-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: customer; Type: TABLE; Schema: public; Owner: hartz_user
--

CREATE TABLE public.customer (
    age integer,
    height double precision,
    weight double precision,
    created_at timestamp(6) without time zone,
    biography character varying(255),
    email character varying(255) NOT NULL,
    mascot character varying(255),
    password character varying(255) NOT NULL,
    plan_type character varying(255) NOT NULL,
    profile_picture character varying(255),
    username character varying(255) NOT NULL,
    CONSTRAINT customer_plan_type_check CHECK (((plan_type)::text = ANY ((ARRAY['BASIC'::character varying, 'PREMIUM'::character varying])::text[])))
);


ALTER TABLE public.customer OWNER TO hartz_user;

--
-- Name: exercise; Type: TABLE; Schema: public; Owner: hartz_user
--

CREATE TABLE public.exercise (
    unilateral boolean NOT NULL,
    body_region character varying(255) NOT NULL,
    difficulty_level character varying(255) NOT NULL,
    equipment character varying(255),
    exercise_name character varying(255) NOT NULL,
    exercise_type character varying(255) NOT NULL,
    grip_type character varying(255),
    CONSTRAINT exercise_body_region_check CHECK (((body_region)::text = ANY ((ARRAY['LOWER'::character varying, 'UPPER'::character varying, 'FULLBODY'::character varying])::text[]))),
    CONSTRAINT exercise_difficulty_level_check CHECK (((difficulty_level)::text = ANY ((ARRAY['INTERMEDIATE'::character varying, 'ADVANCED'::character varying, 'BEGINNER'::character varying])::text[]))),
    CONSTRAINT exercise_equipment_check CHECK (((equipment)::text = ANY ((ARRAY['BARBELL'::character varying, 'PLYO_BOX'::character varying, 'PULLUP_BAR'::character varying, 'SMITH'::character varying, 'DUMBBELL'::character varying, 'MACHINE'::character varying, 'PLATE'::character varying, 'ABDOMINAL_WHEEL'::character varying, 'CABLE'::character varying, 'BAR'::character varying, 'BOX'::character varying, 'PARALLEL_BARS'::character varying, 'Z_BAR'::character varying, 'AIR_BIKE'::character varying, 'TREADMILL'::character varying, 'STATIONARY_BIKE'::character varying, 'HACK_SQUAT'::character varying, 'PENDULUM_SQUAT'::character varying, 'LEG_PRESS'::character varying, 'BENCH'::character varying, 'T_BAR'::character varying, 'RESISTANCE_BAND'::character varying, 'TRAP_BAR'::character varying, 'PEC_DECK'::character varying])::text[]))),
    CONSTRAINT exercise_exercise_type_check CHECK (((exercise_type)::text = ANY ((ARRAY['CARDIO'::character varying, 'WEIGHT_REPS'::character varying, 'TIME'::character varying, 'BODY_REPS'::character varying])::text[]))),
    CONSTRAINT exercise_grip_type_check CHECK (((grip_type)::text = ANY ((ARRAY['PRONATED'::character varying, 'NEUTRAL'::character varying, 'SUPINATED'::character varying, 'MIXED'::character varying, 'SUPINATED_TO_PRONATED'::character varying, 'WIDE_PRONATED'::character varying, 'CLOSE_PRONATED'::character varying])::text[])))
);


ALTER TABLE public.exercise OWNER TO hartz_user;

--
-- Name: exercise_muscle_group; Type: TABLE; Schema: public; Owner: hartz_user
--

CREATE TABLE public.exercise_muscle_group (
    exercise_id character varying(255) NOT NULL,
    muscle_group character varying(255) NOT NULL,
    CONSTRAINT exercise_muscle_group_muscle_group_check CHECK (((muscle_group)::text = ANY ((ARRAY['QUADRICEPS'::character varying, 'GLUTES'::character varying, 'HAMSTIRNGS'::character varying, 'LOWER_BACK'::character varying, 'PECTORALIS_MAJOR'::character varying, 'TRICPES'::character varying, 'ANTERIOR_DELTOID'::character varying, 'LATS'::character varying, 'MIDDLE_TRAPEZIUS'::character varying, 'BICEPS'::character varying, 'RHOMBOIDS'::character varying, 'POSTERIOR_DELTOID'::character varying, 'LATERAL_DELTOID'::character varying, 'LOWER_TRAPEZIUS'::character varying, 'CALVES'::character varying, 'TRAPEZIUS'::character varying, 'DELTOIDS'::character varying, 'ABS'::character varying, 'ABDUCTORS'::character varying, 'ADDUCTORS'::character varying, 'FOREARM'::character varying, 'FULLBODY'::character varying, 'NECK'::character varying, 'SHOULDERS'::character varying, 'GLUTEUS_MEDIUS'::character varying, 'HIP_ABDUCTORS'::character varying, 'REAR_DELTOIDS'::character varying, 'ROTATOR_CUFF'::character varying, 'FRONT_DELTOIDS'::character varying, 'UPPER_CHEST'::character varying, 'CORE'::character varying, 'CHEST'::character varying])::text[])))
);


ALTER TABLE public.exercise_muscle_group OWNER TO hartz_user;

--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: hartz_user
--

COPY public.customer (age, height, weight, created_at, email, mascot, password, plan_type, profile_picture, username) FROM stdin;
\N	\N	\N	2025-07-25 16:30:36.517255	test1@example.com	bear	$2a$10$efFXHbeIm5eD6rN0dz7/O.kyRTONgcM1qDBO95HJbBwJ5kKQ6Ka0u	BASIC	\N	testuser1
\.


--
-- Data for Name: exercise; Type: TABLE DATA; Schema: public; Owner: hartz_user
--

COPY public.exercise (unilateral, body_region, difficulty_level, equipment, exercise_name, exercise_type, grip_type) FROM stdin;
f	LOWER	INTERMEDIATE	BARBELL	Barbell back squat	WEIGHT_REPS	PRONATED
f	LOWER	ADVANCED	BARBELL	Conventional deadlift	WEIGHT_REPS	PRONATED
f	UPPER	INTERMEDIATE	BARBELL	Barbell bench press	WEIGHT_REPS	PRONATED
f	UPPER	INTERMEDIATE	BARBELL	Barbell row	WEIGHT_REPS	PRONATED
f	UPPER	BEGINNER	MACHINE	Machine row (Neutral grip)	WEIGHT_REPS	NEUTRAL
f	FULLBODY	BEGINNER	MACHINE	Rowing machine	CARDIO	PRONATED
f	UPPER	BEGINNER	DUMBBELL	Dumbbell shoulder press	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	SMITH	Smith machine shoulder press	WEIGHT_REPS	PRONATED
f	FULLBODY	INTERMEDIATE	BARBELL	Thrusters with barbell	TIME	PRONATED
f	UPPER	INTERMEDIATE	PULLUP_BAR	Pull-Ups	BODY_REPS	PRONATED
f	FULLBODY	ADVANCED	BARBELL	Clean and jerk	WEIGHT_REPS	PRONATED
f	LOWER	INTERMEDIATE	PLYO_BOX	Box jump	TIME	\N
f	FULLBODY	ADVANCED	BARBELL	Snatch	WEIGHT_REPS	PRONATED
t	UPPER	BEGINNER	PLATE	Russian twist (Weighted)	WEIGHT_REPS	\N
f	UPPER	INTERMEDIATE	PLATE	Incline bench crunches (Weighted)	WEIGHT_REPS	\N
f	UPPER	BEGINNER	\N	Incline bench crunches	WEIGHT_REPS	\N
t	UPPER	BEGINNER	DUMBBELL	Dumbbell side bends	WEIGHT_REPS	\N
t	UPPER	BEGINNER	\N	Scissor abs	WEIGHT_REPS	\N
f	UPPER	INTERMEDIATE	ABDOMINAL_WHEEL	Abdominal wheel	BODY_REPS	\N
t	UPPER	BEGINNER	\N	Bicycle crunches	WEIGHT_REPS	\N
t	UPPER	BEGINNER	\N	Bicycle crunches with raised legs	WEIGHT_REPS	\N
t	UPPER	INTERMEDIATE	MACHINE	Cable core palloff press	WEIGHT_REPS	\N
f	UPPER	BEGINNER	CABLE	Cable crunches	WEIGHT_REPS	\N
t	UPPER	BEGINNER	CABLE	Cable twist (Up to down)	WEIGHT_REPS	\N
t	UPPER	BEGINNER	CABLE	Cable twist (Down to up)	WEIGHT_REPS	\N
f	UPPER	BEGINNER	\N	Seated crunches	WEIGHT_REPS	\N
f	UPPER	INTERMEDIATE	PLATE	Seated crunches (Weighted)	WEIGHT_REPS	\N
f	UPPER	BEGINNER	MACHINE	Seated crunches (Machine)	WEIGHT_REPS	\N
t	UPPER	BEGINNER	\N	Dead bug	WEIGHT_REPS	\N
t	UPPER	ADVANCED	\N	Dragon flag	TIME	\N
t	UPPER	BEGINNER	\N	Knees to elbows	WEIGHT_REPS	\N
t	UPPER	INTERMEDIATE	\N	Leg flapping	WEIGHT_REPS	\N
f	UPPER	INTERMEDIATE	BAR	Knee raises	WEIGHT_REPS	\N
f	UPPER	ADVANCED	BAR	Leg raises	WEIGHT_REPS	\N
t	UPPER	BEGINNER	\N	Heel touch	WEIGHT_REPS	\N
f	UPPER	INTERMEDIATE	\N	Hollow rock	WEIGHT_REPS	\N
f	UPPER	INTERMEDIATE	BOX	Jack knife	TIME	\N
f	UPPER	INTERMEDIATE	\N	V-ups	WEIGHT_REPS	\N
f	UPPER	INTERMEDIATE	PARALLEL_BARS	Knees to chest	WEIGHT_REPS	\N
f	UPPER	ADVANCED	PARALLEL_BARS	Sustained L-sit	TIME	\N
f	UPPER	INTERMEDIATE	PARALLEL_BARS	L-sit	WEIGHT_REPS	\N
t	UPPER	INTERMEDIATE	BARBELL	Landmine 180	WEIGHT_REPS	\N
t	UPPER	BEGINNER	\N	Oblique abdominal	WEIGHT_REPS	\N
f	UPPER	BEGINNER	\N	Plank	TIME	\N
f	UPPER	BEGINNER	PLATE	Weighted plank	TIME	\N
f	LOWER	BEGINNER	MACHINE	Hip abduction	WEIGHT_REPS	\N
f	LOWER	BEGINNER	MACHINE	Hip adduction	WEIGHT_REPS	\N
f	UPPER	BEGINNER	BAR	Behind the back bicep curl	WEIGHT_REPS	\N
f	UPPER	BEGINNER	BAR	Palms-up wrist curl	WEIGHT_REPS	SUPINATED
f	UPPER	BEGINNER	BAR	Palms-down wrist curl	WEIGHT_REPS	PRONATED
f	UPPER	INTERMEDIATE	PLATE	Forearm roller	WEIGHT_REPS	PRONATED
f	UPPER	BEGINNER	BAR	Bar bicep curl	WEIGHT_REPS	SUPINATED
t	UPPER	BEGINNER	CABLE	Cable bicep curl	WEIGHT_REPS	SUPINATED
t	UPPER	BEGINNER	DUMBBELL	Dumbbell bicep curl	WEIGHT_REPS	SUPINATED
f	UPPER	BEGINNER	MACHINE	Machine bicep curl	WEIGHT_REPS	SUPINATED
t	UPPER	BEGINNER	CABLE	Cable hammer bicep curl	WEIGHT_REPS	NEUTRAL
t	UPPER	BEGINNER	DUMBBELL	Dumbbell hammer bicep curl	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	MACHINE	Machine hammer bicep curl	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	Z_BAR	Z-bar hammer bicep curl	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	BARBELL	Preacher curl (Barbell)	WEIGHT_REPS	SUPINATED
t	UPPER	INTERMEDIATE	DUMBBELL	Preacher curl (Dumbbell)	WEIGHT_REPS	SUPINATED
f	UPPER	INTERMEDIATE	MACHINE	Preacher curl (Machine)	WEIGHT_REPS	SUPINATED
t	UPPER	INTERMEDIATE	DUMBBELL	Spider curl (Dumbbell)	WEIGHT_REPS	SUPINATED
f	UPPER	INTERMEDIATE	BARBELL	Spider curl (Barbell)	WEIGHT_REPS	SUPINATED
t	UPPER	INTERMEDIATE	DUMBBELL	Incline bench bicep curl	WEIGHT_REPS	SUPINATED
t	UPPER	INTERMEDIATE	CABLE	Overhead curl	WEIGHT_REPS	SUPINATED
t	UPPER	BEGINNER	CABLE	Plate curl	WEIGHT_REPS	SUPINATED
f	UPPER	INTERMEDIATE	BARBELL	Inverted curl	WEIGHT_REPS	SUPINATED
t	UPPER	INTERMEDIATE	DUMBBELL	Inverted curl (Dumbbell)	WEIGHT_REPS	SUPINATED
t	UPPER	INTERMEDIATE	DUMBBELL	Concentration curl	WEIGHT_REPS	SUPINATED
f	FULLBODY	INTERMEDIATE	AIR_BIKE	Air bike	CARDIO	\N
f	FULLBODY	INTERMEDIATE	MACHINE	Stairs machine	CARDIO	\N
f	FULLBODY	BEGINNER	TREADMILL	Running	CARDIO	\N
f	FULLBODY	BEGINNER	STATIONARY_BIKE	Stationary bike	CARDIO	\N
t	LOWER	INTERMEDIATE	\N	Assisted pistol squat	WEIGHT_REPS	\N
t	LOWER	ADVANCED	\N	Pistol squat	WEIGHT_REPS	\N
t	LOWER	ADVANCED	PLATE	Weighted pistol squat	WEIGHT_REPS	\N
t	LOWER	INTERMEDIATE	DUMBBELL	Bulgarian split squat	WEIGHT_REPS	\N
t	LOWER	INTERMEDIATE	DUMBBELL	Dumbbell lunge	WEIGHT_REPS	\N
t	LOWER	INTERMEDIATE	DUMBBELL	Dumbbell step	WEIGHT_REPS	\N
f	LOWER	INTERMEDIATE	BARBELL	Barbell front squat	WEIGHT_REPS	\N
f	LOWER	INTERMEDIATE	DUMBBELL	Dumbbell front squat	WEIGHT_REPS	\N
f	LOWER	BEGINNER	DUMBBELL	Goblet squat	WEIGHT_REPS	\N
f	LOWER	INTERMEDIATE	HACK_SQUAT	Hack squat	WEIGHT_REPS	\N
f	LOWER	BEGINNER	\N	Jump squat	WEIGHT_REPS	\N
t	LOWER	BEGINNER	\N	Lateral lunge	WEIGHT_REPS	\N
t	LOWER	BEGINNER	\N	Lunges	WEIGHT_REPS	\N
f	LOWER	INTERMEDIATE	PENDULUM_SQUAT	Pendulum squat	WEIGHT_REPS	\N
f	LOWER	BEGINNER	MACHINE	Leg extension	WEIGHT_REPS	\N
f	LOWER	BEGINNER	LEG_PRESS	Leg press	WEIGHT_REPS	\N
f	LOWER	BEGINNER	MACHINE	Seated leg press	WEIGHT_REPS	\N
f	LOWER	BEGINNER	BARBELL	Paused barbell back squat	WEIGHT_REPS	\N
t	LOWER	INTERMEDIATE	BARBELL	Barbell inverted lunges	WEIGHT_REPS	\N
t	LOWER	INTERMEDIATE	\N	Inverted lunges	WEIGHT_REPS	\N
t	LOWER	BEGINNER	MACHINE	Unilateral leg extension	WEIGHT_REPS	\N
f	LOWER	ADVANCED	BENCH	Sissy squat	WEIGHT_REPS	\N
f	LOWER	BEGINNER	SMITH	Back squat (Smith machine)	WEIGHT_REPS	\N
f	LOWER	BEGINNER	SMITH	Front squat (Smith machine)	WEIGHT_REPS	\N
t	LOWER	BEGINNER	BOX	Step up	WEIGHT_REPS	\N
f	LOWER	BEGINNER	\N	Sumo squat	WEIGHT_REPS	\N
f	LOWER	BEGINNER	BARBELL	Barbell sumo squat	WEIGHT_REPS	\N
f	LOWER	BEGINNER	DUMBBELL	Dumbbell sumo squat	WEIGHT_REPS	\N
f	LOWER	BEGINNER	\N	Wall sit	TIME	\N
f	LOWER	INTERMEDIATE	BARBELL	Zercher squat	WEIGHT_REPS	\N
f	UPPER	BEGINNER	\N	Lying neck curls	WEIGHT_REPS	\N
f	UPPER	BEGINNER	\N	Lying neck extensions	WEIGHT_REPS	\N
f	UPPER	BEGINNER	PLATE	Lying neck curls (Weighted)	WEIGHT_REPS	\N
f	UPPER	BEGINNER	PLATE	Lying neck extensions (Weighted)	WEIGHT_REPS	\N
f	UPPER	BEGINNER	MACHINE	Lat pulldowns (Machine)	WEIGHT_REPS	PRONATED
f	UPPER	BEGINNER	MACHINE	Chin ups (Machine)	WEIGHT_REPS	SUPINATED
f	UPPER	BEGINNER	MACHINE	Neutral pulldowns (Machine)	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	MACHINE	Wide lat pulldowns (Machine)	WEIGHT_REPS	PRONATED
f	UPPER	BEGINNER	PULLUP_BAR	Chin ups	WEIGHT_REPS	SUPINATED
t	UPPER	BEGINNER	DUMBBELL	Dumbbell row (Unilateral)	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	MACHINE	Assisted pull-ups	WEIGHT_REPS	PRONATED
f	UPPER	BEGINNER	MACHINE	Assisted chin-ups	WEIGHT_REPS	SUPINATED
t	UPPER	INTERMEDIATE	MACHINE	High row (Iso-lateral)	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	PULLUP_BAR	Negative pull-ups	WEIGHT_REPS	PRONATED
f	UPPER	BEGINNER	PULLUP_BAR	Band-assisted pull-ups	WEIGHT_REPS	PRONATED
f	UPPER	ADVANCED	PULLUP_BAR	Weighted pull-ups	WEIGHT_REPS	PRONATED
f	UPPER	INTERMEDIATE	CABLE	Cable pullover	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	MACHINE	Machine pullover	WEIGHT_REPS	NEUTRAL
t	UPPER	INTERMEDIATE	CABLE	Cable pullover (Unilateral)	WEIGHT_REPS	NEUTRAL
f	LOWER	BEGINNER	MACHINE	Back extension (Machine)	WEIGHT_REPS	\N
f	LOWER	INTERMEDIATE	PLATE	Back extension (Weighted)	WEIGHT_REPS	\N
f	LOWER	BEGINNER	\N	Superman holds	TIME	\N
f	LOWER	BEGINNER	\N	Superman	WEIGHT_REPS	\N
f	UPPER	BEGINNER	MACHINE	Seated row (Machine, wide grip)	WEIGHT_REPS	PRONATED
f	UPPER	BEGINNER	CABLE	Seated cable row	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	BARBELL	Bent-over barbell row	WEIGHT_REPS	PRONATED
f	UPPER	INTERMEDIATE	DUMBBELL	Bent-over dumbbell row	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	DUMBBELL	Chest-supported dumbbell row	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	PULLUP_BAR	Dead hang	TIME	PRONATED
t	UPPER	INTERMEDIATE	DUMBBELL	Gorilla row (Dumbbells)	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	BARBELL	Inverted row	WEIGHT_REPS	SUPINATED
t	UPPER	INTERMEDIATE	MACHINE	Low row (Iso-lateral)	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	BARBELL	Landmine row (T-bar row)	WEIGHT_REPS	NEUTRAL
f	LOWER	INTERMEDIATE	BARBELL	Rack pull	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	PULLUP_BAR	Scapular pull-ups	WEIGHT_REPS	PRONATED
f	UPPER	BEGINNER	CABLE	Seated cable row (Wide grip)	WEIGHT_REPS	PRONATED
f	UPPER	BEGINNER	CABLE	Seated cable row (V-grip)	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	T_BAR	T-bar row	WEIGHT_REPS	NEUTRAL
f	LOWER	BEGINNER	MACHINE	Calf extension (Machine)	WEIGHT_REPS	\N
f	LOWER	BEGINNER	MACHINE	Calf press (Leg press machine)	WEIGHT_REPS	\N
f	LOWER	BEGINNER	MACHINE	Seated calf raise	WEIGHT_REPS	\N
t	LOWER	BEGINNER	\N	Single-leg calf raise	WEIGHT_REPS	\N
t	LOWER	INTERMEDIATE	BARBELL	Single-leg calf raise (Barbell)	WEIGHT_REPS	\N
t	LOWER	BEGINNER	DUMBBELL	Single-leg calf raise (Dumbbell)	WEIGHT_REPS	\N
f	LOWER	BEGINNER	SMITH	Standing calf raise (Smith machine)	WEIGHT_REPS	\N
f	LOWER	BEGINNER	DUMBBELL	Standing calf raise (Dumbbells)	WEIGHT_REPS	\N
t	LOWER	BEGINNER	MACHINE	Single-leg calf raise (Machine)	WEIGHT_REPS	\N
f	LOWER	INTERMEDIATE	BARBELL	Sumo deadlift	WEIGHT_REPS	MIXED
t	LOWER	BEGINNER	CABLE	Cable glute kickback	WEIGHT_REPS	\N
t	LOWER	BEGINNER	MACHINE	Glute kickback (Machine)	WEIGHT_REPS	\N
t	LOWER	INTERMEDIATE	DUMBBELL	Single-leg hip thrust (Dumbbell)	WEIGHT_REPS	\N
f	LOWER	INTERMEDIATE	MACHINE	Reverse hyperextension (Glute focus)	WEIGHT_REPS	\N
t	LOWER	BEGINNER	CABLE	Cable lateral leg raise	WEIGHT_REPS	\N
f	LOWER	BEGINNER	RESISTANCE_BAND	Lateral band walk	WEIGHT_REPS	\N
f	LOWER	INTERMEDIATE	BARBELL	Barbell hip thrust	WEIGHT_REPS	\N
f	LOWER	BEGINNER	BENCH	Bodyweight hip thrust	WEIGHT_REPS	\N
t	LOWER	BEGINNER	\N	Donkey kick (Bodyweight)	WEIGHT_REPS	\N
f	LOWER	BEGINNER	DUMBBELL	Frog pumps (Dumbbell)	WEIGHT_REPS	\N
f	LOWER	INTERMEDIATE	TRAP_BAR	Trap bar deadlift	WEIGHT_REPS	NEUTRAL
f	LOWER	BEGINNER	SMITH	Smith machine deadlift	WEIGHT_REPS	PRONATED
f	LOWER	BEGINNER	DUMBBELL	Dumbbell deadlift	WEIGHT_REPS	NEUTRAL
f	LOWER	INTERMEDIATE	CABLE	Cable pull-through	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	BARBELL	Barbell shoulder press	WEIGHT_REPS	PRONATED
f	UPPER	BEGINNER	MACHINE	Machine shoulder press	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	DUMBBELL	Arnold press (Dumbbells)	WEIGHT_REPS	SUPINATED_TO_PRONATED
f	UPPER	BEGINNER	DUMBBELL	Rear delt fly (Dumbbells)	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	MACHINE	Rear delt fly (Machine)	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	CABLE	Face pull	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	CABLE	Front raise (Cable)	WEIGHT_REPS	PRONATED
f	UPPER	BEGINNER	DUMBBELL	Front raise (Dumbbells)	WEIGHT_REPS	PRONATED
t	UPPER	BEGINNER	CABLE	Lateral raise (Cable)	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	DUMBBELL	Lateral raise (Dumbbells)	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	MACHINE	Lateral raise (Machine)	WEIGHT_REPS	\N
f	UPPER	INTERMEDIATE	\N	Pike push-up	WEIGHT_REPS	\N
f	UPPER	BEGINNER	SMITH	Military press (Smith machine)	WEIGHT_REPS	PRONATED
f	LOWER	INTERMEDIATE	BARBELL	Romanian deadlift (Barbell)	WEIGHT_REPS	MIXED
f	LOWER	INTERMEDIATE	DUMBBELL	Romanian deadlift (Dumbbells)	WEIGHT_REPS	NEUTRAL
f	LOWER	BEGINNER	MACHINE	Lying leg curl (Machine)	WEIGHT_REPS	\N
f	LOWER	ADVANCED	\N	Nordic hamstring curl	WEIGHT_REPS	\N
t	LOWER	INTERMEDIATE	DUMBBELL	Single-leg Romanian deadlift (Dumbbell)	WEIGHT_REPS	NEUTRAL
f	LOWER	BEGINNER	MACHINE	Seated leg curl (Machine)	WEIGHT_REPS	\N
f	UPPER	BEGINNER	DUMBBELL	Incline bench press (Dumbbells)	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	BARBELL	Incline bench press (Barbell)	WEIGHT_REPS	PRONATED
f	UPPER	BEGINNER	DUMBBELL	Flat bench press (Dumbbells)	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	PEC_DECK	Chest fly (Machine)	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	DUMBBELL	Chest fly (Dumbbells)	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	MACHINE	Incline bench press (Machine)	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	MACHINE	Flat bench press (Machine)	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	\N	Push-ups (Bodyweight)	WEIGHT_REPS	\N
f	UPPER	INTERMEDIATE	BARBELL	Flat bench press (Wide grip, Barbell)	WEIGHT_REPS	WIDE_PRONATED
f	UPPER	BEGINNER	SMITH	Flat bench press (Smith machine)	WEIGHT_REPS	PRONATED
f	UPPER	BEGINNER	SMITH	Incline bench press (Smith machine)	WEIGHT_REPS	PRONATED
f	UPPER	INTERMEDIATE	CABLE	Cable chest fly (Crossover)	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	PARALLEL_BARS	Parallel bar dips (Bodyweight)	WEIGHT_REPS	NEUTRAL
f	UPPER	ADVANCED	PARALLEL_BARS	Weighted dips (Parallel bars)	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	BARBELL	Decline bench press (Barbell)	WEIGHT_REPS	PRONATED
f	UPPER	INTERMEDIATE	DUMBBELL	Decline bench press (Dumbbells)	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	DUMBBELL	Hex press (Single Dumbbell)	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	BOX	Incline push-ups (Box or Bench)	WEIGHT_REPS	\N
t	UPPER	ADVANCED	\N	One-arm push-up	WEIGHT_REPS	\N
t	UPPER	INTERMEDIATE	CABLE	Unilateral cable chest fly	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	PLATE	Weighted push-ups (Floor)	WEIGHT_REPS	\N
f	UPPER	BEGINNER	BARBELL	Barbell shrugs	WEIGHT_REPS	PRONATED
f	UPPER	BEGINNER	DUMBBELL	Dumbbell shrugs	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	CABLE	Cable shrugs	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	MACHINE	Machine shrugs	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	SMITH	Smith machine shrugs	WEIGHT_REPS	PRONATED
f	UPPER	BEGINNER	BENCH	Bench dips (Bodyweight)	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	BARBELL	Close-grip bench press (Barbell)	WEIGHT_REPS	CLOSE_PRONATED
f	UPPER	BEGINNER	\N	Diamond push-ups	WEIGHT_REPS	\N
f	UPPER	BEGINNER	\N	Triceps floor dips	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	BARBELL	JM press (Barbell)	WEIGHT_REPS	PRONATED
f	UPPER	BEGINNER	MACHINE	Machine dips	WEIGHT_REPS	NEUTRAL
t	UPPER	BEGINNER	DUMBBELL	One-arm dumbbell triceps extension	WEIGHT_REPS	NEUTRAL
t	UPPER	BEGINNER	CABLE	One-arm cable triceps extension	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	BARBELL	French press (Barbell)	WEIGHT_REPS	PRONATED
f	UPPER	INTERMEDIATE	DUMBBELL	French press (Dumbbell)	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	MACHINE	Assisted machine dips (Triceps)	WEIGHT_REPS	NEUTRAL
f	UPPER	INTERMEDIATE	BARBELL	Overhead triceps extension (Barbell)	WEIGHT_REPS	PRONATED
t	UPPER	INTERMEDIATE	CABLE	One-arm overhead triceps extension (Cable)	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	MACHINE	Triceps extension (Machine)	WEIGHT_REPS	NEUTRAL
t	UPPER	BEGINNER	CABLE	Triceps kickback (Cable)	WEIGHT_REPS	NEUTRAL
t	UPPER	BEGINNER	DUMBBELL	Triceps kickback (Dumbbell)	WEIGHT_REPS	NEUTRAL
f	UPPER	BEGINNER	CABLE	Two-hand cable triceps pushdown	WEIGHT_REPS	PRONATED
\.


--
-- Data for Name: exercise_muscle_group; Type: TABLE DATA; Schema: public; Owner: hartz_user
--

COPY public.exercise_muscle_group (exercise_id, muscle_group) FROM stdin;
Barbell back squat	QUADRICEPS
Barbell back squat	GLUTES
Conventional deadlift	GLUTES
Conventional deadlift	HAMSTIRNGS
Conventional deadlift	LOWER_BACK
Barbell bench press	PECTORALIS_MAJOR
Barbell bench press	TRICPES
Barbell bench press	ANTERIOR_DELTOID
Barbell row	LATS
Barbell row	MIDDLE_TRAPEZIUS
Barbell row	BICEPS
Machine row (Neutral grip)	LATS
Machine row (Neutral grip)	RHOMBOIDS
Machine row (Neutral grip)	BICEPS
Rowing machine	LATS
Rowing machine	POSTERIOR_DELTOID
Rowing machine	QUADRICEPS
Rowing machine	GLUTES
Smith machine shoulder press	ANTERIOR_DELTOID
Smith machine shoulder press	TRICPES
Thrusters with barbell	QUADRICEPS
Thrusters with barbell	GLUTES
Thrusters with barbell	DELTOIDS
Thrusters with barbell	TRICPES
Pull-Ups	LATS
Pull-Ups	BICEPS
Pull-Ups	LOWER_TRAPEZIUS
Clean and jerk	TRAPEZIUS
Clean and jerk	GLUTES
Clean and jerk	DELTOIDS
Clean and jerk	QUADRICEPS
Box jump	QUADRICEPS
Box jump	GLUTES
Box jump	CALVES
Snatch	TRAPEZIUS
Snatch	DELTOIDS
Snatch	GLUTES
Snatch	HAMSTIRNGS
Russian twist (Weighted)	ABS
Incline bench crunches (Weighted)	ABS
Incline bench crunches	ABS
Dumbbell side bends	ABS
Scissor abs	ABS
Abdominal wheel	ABS
Bicycle crunches	ABS
Bicycle crunches with raised legs	ABS
Cable core palloff press	ABS
Cable crunches	ABS
Cable twist (Up to down)	ABS
Cable twist (Down to up)	ABS
Seated crunches	ABS
Seated crunches (Weighted)	ABS
Seated crunches (Machine)	ABS
Dead bug	ABS
Dragon flag	ABS
Knees to elbows	ABS
Leg flapping	ABS
Knee raises	ABS
Leg raises	ABS
Heel touch	ABS
Hollow rock	ABS
Jack knife	ABS
V-ups	ABS
Knees to chest	ABS
Sustained L-sit	ABS
L-sit	ABS
Landmine 180	ABS
Oblique abdominal	ABS
Plank	ABS
Weighted plank	ABS
Hip abduction	ABDUCTORS
Hip adduction	ADDUCTORS
Behind the back bicep curl	FOREARM
Palms-up wrist curl	FOREARM
Palms-down wrist curl	FOREARM
Forearm roller	FOREARM
Bar bicep curl	BICEPS
Bar bicep curl	FOREARM
Cable bicep curl	BICEPS
Cable bicep curl	FOREARM
Dumbbell bicep curl	BICEPS
Dumbbell bicep curl	FOREARM
Machine bicep curl	BICEPS
Machine bicep curl	FOREARM
Cable hammer bicep curl	BICEPS
Cable hammer bicep curl	FOREARM
Dumbbell hammer bicep curl	BICEPS
Dumbbell hammer bicep curl	FOREARM
Machine hammer bicep curl	BICEPS
Machine hammer bicep curl	FOREARM
Z-bar hammer bicep curl	BICEPS
Z-bar hammer bicep curl	FOREARM
Preacher curl (Barbell)	BICEPS
Preacher curl (Barbell)	FOREARM
Preacher curl (Dumbbell)	BICEPS
Preacher curl (Dumbbell)	FOREARM
Preacher curl (Machine)	BICEPS
Preacher curl (Machine)	FOREARM
Spider curl (Dumbbell)	BICEPS
Spider curl (Dumbbell)	FOREARM
Spider curl (Barbell)	BICEPS
Spider curl (Barbell)	FOREARM
Incline bench bicep curl	BICEPS
Incline bench bicep curl	FOREARM
Overhead curl	BICEPS
Overhead curl	FOREARM
Plate curl	BICEPS
Plate curl	FOREARM
Inverted curl	BICEPS
Inverted curl	FOREARM
Inverted curl (Dumbbell)	BICEPS
Inverted curl (Dumbbell)	FOREARM
Concentration curl	BICEPS
Concentration curl	FOREARM
Air bike	FULLBODY
Stairs machine	FULLBODY
Running	FULLBODY
Stationary bike	FULLBODY
Assisted pistol squat	QUADRICEPS
Assisted pistol squat	GLUTES
Pistol squat	QUADRICEPS
Pistol squat	GLUTES
Weighted pistol squat	QUADRICEPS
Weighted pistol squat	GLUTES
Bulgarian split squat	QUADRICEPS
Bulgarian split squat	GLUTES
Dumbbell lunge	QUADRICEPS
Dumbbell lunge	GLUTES
Dumbbell lunge	HAMSTIRNGS
Dumbbell step	GLUTES
Dumbbell step	HAMSTIRNGS
Barbell front squat	GLUTES
Barbell front squat	QUADRICEPS
Dumbbell front squat	GLUTES
Dumbbell front squat	QUADRICEPS
Goblet squat	GLUTES
Goblet squat	QUADRICEPS
Hack squat	GLUTES
Hack squat	QUADRICEPS
Hack squat	HAMSTIRNGS
Jump squat	GLUTES
Jump squat	QUADRICEPS
Jump squat	HAMSTIRNGS
Lateral lunge	GLUTES
Lateral lunge	QUADRICEPS
Lateral lunge	HAMSTIRNGS
Lateral lunge	ABDUCTORS
Lunges	GLUTES
Lunges	QUADRICEPS
Lunges	HAMSTIRNGS
Pendulum squat	GLUTES
Pendulum squat	QUADRICEPS
Pendulum squat	HAMSTIRNGS
Leg extension	QUADRICEPS
Leg press	QUADRICEPS
Leg press	GLUTES
Leg press	HAMSTIRNGS
Seated leg press	QUADRICEPS
Seated leg press	GLUTES
Seated leg press	HAMSTIRNGS
Paused barbell back squat	QUADRICEPS
Paused barbell back squat	GLUTES
Paused barbell back squat	HAMSTIRNGS
Barbell inverted lunges	QUADRICEPS
Barbell inverted lunges	GLUTES
Barbell inverted lunges	HAMSTIRNGS
Inverted lunges	QUADRICEPS
Inverted lunges	GLUTES
Inverted lunges	HAMSTIRNGS
Unilateral leg extension	QUADRICEPS
Sissy squat	QUADRICEPS
Back squat (Smith machine)	QUADRICEPS
Back squat (Smith machine)	HAMSTIRNGS
Back squat (Smith machine)	GLUTES
Front squat (Smith machine)	QUADRICEPS
Step up	GLUTES
Step up	HAMSTIRNGS
Sumo squat	GLUTES
Sumo squat	QUADRICEPS
Sumo squat	HAMSTIRNGS
Barbell sumo squat	GLUTES
Barbell sumo squat	QUADRICEPS
Barbell sumo squat	HAMSTIRNGS
Dumbbell sumo squat	GLUTES
Dumbbell sumo squat	QUADRICEPS
Dumbbell sumo squat	HAMSTIRNGS
Wall sit	GLUTES
Wall sit	QUADRICEPS
Wall sit	HAMSTIRNGS
Zercher squat	GLUTES
Zercher squat	QUADRICEPS
Zercher squat	HAMSTIRNGS
Lying neck curls	NECK
Lying neck extensions	NECK
Lying neck curls (Weighted)	NECK
Lying neck extensions (Weighted)	NECK
Lat pulldowns (Machine)	LATS
Chin ups (Machine)	LOWER_BACK
Neutral pulldowns (Machine)	LATS
Wide lat pulldowns (Machine)	LATS
Chin ups	LATS
Dumbbell row (Unilateral)	LATS
Dumbbell row (Unilateral)	BICEPS
Assisted pull-ups	LATS
Assisted pull-ups	BICEPS
Assisted chin-ups	LATS
Assisted chin-ups	BICEPS
High row (Iso-lateral)	LATS
High row (Iso-lateral)	TRAPEZIUS
High row (Iso-lateral)	RHOMBOIDS
Negative pull-ups	LATS
Negative pull-ups	BICEPS
Band-assisted pull-ups	LATS
Band-assisted pull-ups	BICEPS
Weighted pull-ups	LATS
Weighted pull-ups	BICEPS
Cable pullover	LATS
Machine pullover	LATS
Cable pullover (Unilateral)	LATS
Back extension (Machine)	LOWER_BACK
Back extension (Machine)	GLUTES
Back extension (Machine)	HAMSTIRNGS
Back extension (Weighted)	LOWER_BACK
Back extension (Weighted)	GLUTES
Back extension (Weighted)	HAMSTIRNGS
Superman holds	LOWER_BACK
Superman holds	GLUTES
Superman	LOWER_BACK
Superman	GLUTES
Seated row (Machine, wide grip)	LATS
Seated row (Machine, wide grip)	RHOMBOIDS
Seated row (Machine, wide grip)	BICEPS
Seated cable row	LATS
Seated cable row	RHOMBOIDS
Seated cable row	BICEPS
Bent-over barbell row	LATS
Bent-over barbell row	RHOMBOIDS
Bent-over barbell row	LOWER_BACK
Bent-over dumbbell row	LATS
Bent-over dumbbell row	RHOMBOIDS
Bent-over dumbbell row	LOWER_BACK
Chest-supported dumbbell row	LATS
Chest-supported dumbbell row	RHOMBOIDS
Chest-supported dumbbell row	TRAPEZIUS
Dead hang	FOREARM
Dead hang	SHOULDERS
Dead hang	LATS
Gorilla row (Dumbbells)	LATS
Gorilla row (Dumbbells)	RHOMBOIDS
Gorilla row (Dumbbells)	BICEPS
Inverted row	LATS
Inverted row	RHOMBOIDS
Inverted row	BICEPS
Low row (Iso-lateral)	LATS
Low row (Iso-lateral)	RHOMBOIDS
Low row (Iso-lateral)	BICEPS
Landmine row (T-bar row)	LATS
Landmine row (T-bar row)	RHOMBOIDS
Landmine row (T-bar row)	LOWER_BACK
Rack pull	LOWER_BACK
Rack pull	GLUTES
Rack pull	HAMSTIRNGS
Rack pull	TRAPEZIUS
Scapular pull-ups	TRAPEZIUS
Scapular pull-ups	LATS
Scapular pull-ups	RHOMBOIDS
Seated cable row (Wide grip)	LATS
Seated cable row (Wide grip)	RHOMBOIDS
Seated cable row (Wide grip)	BICEPS
Seated cable row (V-grip)	LATS
Seated cable row (V-grip)	RHOMBOIDS
Seated cable row (V-grip)	BICEPS
T-bar row	LATS
T-bar row	RHOMBOIDS
T-bar row	LOWER_BACK
Calf extension (Machine)	CALVES
Calf press (Leg press machine)	CALVES
Seated calf raise	CALVES
Single-leg calf raise	CALVES
Single-leg calf raise (Barbell)	CALVES
Single-leg calf raise (Dumbbell)	CALVES
Standing calf raise (Smith machine)	CALVES
Standing calf raise (Dumbbells)	CALVES
Single-leg calf raise (Machine)	CALVES
Sumo deadlift	GLUTES
Sumo deadlift	HAMSTIRNGS
Sumo deadlift	QUADRICEPS
Sumo deadlift	LOWER_BACK
Cable glute kickback	GLUTES
Cable glute kickback	HAMSTIRNGS
Glute kickback (Machine)	GLUTES
Glute kickback (Machine)	HAMSTIRNGS
Single-leg hip thrust (Dumbbell)	GLUTES
Single-leg hip thrust (Dumbbell)	HAMSTIRNGS
Reverse hyperextension (Glute focus)	GLUTES
Reverse hyperextension (Glute focus)	LOWER_BACK
Reverse hyperextension (Glute focus)	HAMSTIRNGS
Cable lateral leg raise	GLUTEUS_MEDIUS
Cable lateral leg raise	HIP_ABDUCTORS
Lateral band walk	GLUTEUS_MEDIUS
Lateral band walk	HIP_ABDUCTORS
Barbell hip thrust	GLUTES
Barbell hip thrust	HAMSTIRNGS
Bodyweight hip thrust	GLUTES
Bodyweight hip thrust	HAMSTIRNGS
Donkey kick (Bodyweight)	GLUTES
Frog pumps (Dumbbell)	GLUTES
Trap bar deadlift	GLUTES
Trap bar deadlift	HAMSTIRNGS
Trap bar deadlift	QUADRICEPS
Trap bar deadlift	LOWER_BACK
Smith machine deadlift	GLUTES
Smith machine deadlift	HAMSTIRNGS
Smith machine deadlift	LOWER_BACK
Dumbbell deadlift	GLUTES
Dumbbell deadlift	HAMSTIRNGS
Dumbbell deadlift	LOWER_BACK
Cable pull-through	GLUTES
Cable pull-through	HAMSTIRNGS
Dumbbell shoulder press	DELTOIDS
Dumbbell shoulder press	TRICPES
Barbell shoulder press	DELTOIDS
Barbell shoulder press	TRICPES
Machine shoulder press	DELTOIDS
Machine shoulder press	TRICPES
Arnold press (Dumbbells)	DELTOIDS
Arnold press (Dumbbells)	TRICPES
Rear delt fly (Dumbbells)	REAR_DELTOIDS
Rear delt fly (Dumbbells)	TRAPEZIUS
Rear delt fly (Machine)	REAR_DELTOIDS
Rear delt fly (Machine)	TRAPEZIUS
Face pull	REAR_DELTOIDS
Face pull	TRAPEZIUS
Face pull	ROTATOR_CUFF
Front raise (Cable)	FRONT_DELTOIDS
Front raise (Dumbbells)	FRONT_DELTOIDS
Lateral raise (Cable)	LATERAL_DELTOID
Lateral raise (Dumbbells)	LATERAL_DELTOID
Lateral raise (Machine)	LATERAL_DELTOID
Pike push-up	DELTOIDS
Pike push-up	TRICPES
Pike push-up	UPPER_CHEST
Military press (Smith machine)	DELTOIDS
Military press (Smith machine)	TRICPES
Romanian deadlift (Barbell)	HAMSTIRNGS
Romanian deadlift (Barbell)	GLUTES
Romanian deadlift (Barbell)	LOWER_BACK
Romanian deadlift (Dumbbells)	HAMSTIRNGS
Romanian deadlift (Dumbbells)	GLUTES
Romanian deadlift (Dumbbells)	LOWER_BACK
Lying leg curl (Machine)	HAMSTIRNGS
Nordic hamstring curl	HAMSTIRNGS
Single-leg Romanian deadlift (Dumbbell)	HAMSTIRNGS
Single-leg Romanian deadlift (Dumbbell)	GLUTES
Single-leg Romanian deadlift (Dumbbell)	CORE
Seated leg curl (Machine)	HAMSTIRNGS
Incline bench press (Dumbbells)	CHEST
Incline bench press (Dumbbells)	TRICPES
Incline bench press (Dumbbells)	FRONT_DELTOIDS
Incline bench press (Barbell)	CHEST
Incline bench press (Barbell)	TRICPES
Incline bench press (Barbell)	FRONT_DELTOIDS
Flat bench press (Dumbbells)	CHEST
Flat bench press (Dumbbells)	TRICPES
Flat bench press (Dumbbells)	FRONT_DELTOIDS
Chest fly (Machine)	CHEST
Chest fly (Dumbbells)	CHEST
Incline bench press (Machine)	CHEST
Incline bench press (Machine)	TRICPES
Incline bench press (Machine)	FRONT_DELTOIDS
Flat bench press (Machine)	CHEST
Flat bench press (Machine)	TRICPES
Flat bench press (Machine)	FRONT_DELTOIDS
Push-ups (Bodyweight)	CHEST
Push-ups (Bodyweight)	TRICPES
Push-ups (Bodyweight)	CORE
Flat bench press (Wide grip, Barbell)	CHEST
Flat bench press (Wide grip, Barbell)	TRICPES
Flat bench press (Wide grip, Barbell)	FRONT_DELTOIDS
Flat bench press (Smith machine)	CHEST
Flat bench press (Smith machine)	TRICPES
Flat bench press (Smith machine)	FRONT_DELTOIDS
Incline bench press (Smith machine)	CHEST
Incline bench press (Smith machine)	TRICPES
Incline bench press (Smith machine)	FRONT_DELTOIDS
Cable chest fly (Crossover)	CHEST
Parallel bar dips (Bodyweight)	CHEST
Parallel bar dips (Bodyweight)	TRICPES
Parallel bar dips (Bodyweight)	FRONT_DELTOIDS
Weighted dips (Parallel bars)	CHEST
Weighted dips (Parallel bars)	TRICPES
Weighted dips (Parallel bars)	FRONT_DELTOIDS
Decline bench press (Barbell)	CHEST
Decline bench press (Barbell)	TRICPES
Decline bench press (Dumbbells)	CHEST
Decline bench press (Dumbbells)	TRICPES
Hex press (Single Dumbbell)	CHEST
Hex press (Single Dumbbell)	TRICPES
Incline push-ups (Box or Bench)	CHEST
Incline push-ups (Box or Bench)	TRICPES
Incline push-ups (Box or Bench)	SHOULDERS
One-arm push-up	CHEST
One-arm push-up	TRICPES
One-arm push-up	CORE
Unilateral cable chest fly	CHEST
Weighted push-ups (Floor)	CHEST
Weighted push-ups (Floor)	TRICPES
Weighted push-ups (Floor)	CORE
Barbell shrugs	TRAPEZIUS
Dumbbell shrugs	TRAPEZIUS
Cable shrugs	TRAPEZIUS
Machine shrugs	TRAPEZIUS
Smith machine shrugs	TRAPEZIUS
Bench dips (Bodyweight)	TRICPES
Bench dips (Bodyweight)	CHEST
Bench dips (Bodyweight)	SHOULDERS
Close-grip bench press (Barbell)	TRICPES
Close-grip bench press (Barbell)	CHEST
Close-grip bench press (Barbell)	FRONT_DELTOIDS
Diamond push-ups	TRICPES
Diamond push-ups	CHEST
Diamond push-ups	CORE
Triceps floor dips	TRICPES
Triceps floor dips	CHEST
JM press (Barbell)	TRICPES
JM press (Barbell)	CHEST
JM press (Barbell)	FRONT_DELTOIDS
Machine dips	TRICPES
Machine dips	CHEST
Machine dips	SHOULDERS
One-arm dumbbell triceps extension	TRICPES
One-arm cable triceps extension	TRICPES
French press (Barbell)	TRICPES
French press (Dumbbell)	TRICPES
Assisted machine dips (Triceps)	TRICPES
Assisted machine dips (Triceps)	CHEST
Assisted machine dips (Triceps)	SHOULDERS
Overhead triceps extension (Barbell)	TRICPES
One-arm overhead triceps extension (Cable)	TRICPES
Triceps extension (Machine)	TRICPES
Triceps kickback (Cable)	TRICPES
Triceps kickback (Dumbbell)	TRICPES
Two-hand cable triceps pushdown	TRICPES
\.


--
-- Name: customer customer_email_key; Type: CONSTRAINT; Schema: public; Owner: hartz_user
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_email_key UNIQUE (email);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: hartz_user
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (username);


--
-- Name: exercise exercise_pkey; Type: CONSTRAINT; Schema: public; Owner: hartz_user
--

ALTER TABLE ONLY public.exercise
    ADD CONSTRAINT exercise_pkey PRIMARY KEY (exercise_name);


--
-- Name: exercise_muscle_group fkhef4g2bhj69l6n0xqwftw2u5s; Type: FK CONSTRAINT; Schema: public; Owner: hartz_user
--

ALTER TABLE ONLY public.exercise_muscle_group
    ADD CONSTRAINT fkhef4g2bhj69l6n0xqwftw2u5s FOREIGN KEY (exercise_id) REFERENCES public.exercise(exercise_name);


--
-- PostgreSQL database dump complete
--

