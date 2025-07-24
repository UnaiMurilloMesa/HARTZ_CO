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
    grip_type character varying(255),
    CONSTRAINT exercise_body_region_check CHECK (((body_region)::text = ANY ((ARRAY['LOWER'::character varying, 'UPPER'::character varying, 'FULLBODY'::character varying])::text[]))),
    CONSTRAINT exercise_difficulty_level_check CHECK (((difficulty_level)::text = ANY ((ARRAY['INTERMEDIATE'::character varying, 'ADVANCED'::character varying, 'BEGINNER'::character varying])::text[]))),
    CONSTRAINT exercise_equipment_check CHECK (((equipment)::text = ANY ((ARRAY['BARBELL'::character varying, 'PLYO_BOX'::character varying, 'PULLUP_BAR'::character varying, 'SMITH_MACHINE'::character varying, 'DUMBBELL'::character varying, 'ROWING_MACHINE'::character varying, 'MACHINE'::character varying, 'PLATE'::character varying, 'ABDOMINAL_WHEEL'::character varying, 'CABLE'::character varying, 'BAR'::character varying, 'BOX'::character varying, 'PARALLEL_BARS'::character varying, 'Z_BAR'::character varying, 'AIR_BIKE'::character varying, 'STAIRS_MACHINE'::character varying, 'TREADMILL'::character varying, 'STATIONARY_BIKE'::character varying, 'HACK_SQUAT'::character varying, 'PENDULUM_SQUAT'::character varying, 'LEG_EXTENSION_MACHINE'::character varying, 'LEG_PRESS'::character varying, 'LEG_PRESS_MACHINE'::character varying, 'BENCH'::character varying, 'LAT_PULLDOWN_MACHINE'::character varying, 'ASSISTED_PULLUP_MACHINE'::character varying, 'ISO_LATERAL_ROW_MACHINE'::character varying, 'PULLOVER_MACHINE'::character varying, 'BACK_EXTENSION_MACHINE'::character varying, 'SEATED_ROW_MACHINE'::character varying, 'T_BAR'::character varying, 'CALF_RAISE_MACHINE'::character varying, 'SEATED_CALF_RAISE_MACHINE'::character varying, 'GLUTE_KICKBACK_MACHINE'::character varying, 'REVERSE_HYPEREXTENSION_MACHINE'::character varying, 'RESISTANCE_BAND'::character varying, 'TRAP_BAR'::character varying, 'SHOULDER_PRESS_MACHINE'::character varying, 'REAR_DELT_MACHINE'::character varying, 'LATERAL_RAISE_MACHINE'::character varying, 'LEG_CURL_MACHINE'::character varying, 'SEATED_LEG_CURL_MACHINE'::character varying, 'PEC_DECK'::character varying, 'INCLINE_CHEST_PRESS_MACHINE'::character varying, 'CHEST_PRESS_MACHINE'::character varying, 'SHRUG_MACHINE'::character varying, 'DIP_MACHINE'::character varying, 'ASSISTED_DIP_MACHINE'::character varying, 'TRICEPS_EXTENSION_MACHINE'::character varying])::text[]))),
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
\.


--
-- Data for Name: exercise; Type: TABLE DATA; Schema: public; Owner: hartz_user
--

COPY public.exercise (unilateral, body_region, difficulty_level, equipment, exercise_name, grip_type) FROM stdin;
f	LOWER	INTERMEDIATE	BARBELL	Barbell back squat	PRONATED
f	LOWER	ADVANCED	BARBELL	Conventional deadlift	PRONATED
f	UPPER	INTERMEDIATE	BARBELL	Barbell bench press	PRONATED
f	UPPER	INTERMEDIATE	BARBELL	Barbell row	PRONATED
f	UPPER	BEGINNER	MACHINE	Machine row (Neutral grip)	NEUTRAL
f	FULLBODY	BEGINNER	ROWING_MACHINE	Rowing machine	PRONATED
f	UPPER	BEGINNER	DUMBBELL	Dumbbell shoulder press	NEUTRAL
f	UPPER	INTERMEDIATE	SMITH_MACHINE	Smith machine shoulder press	PRONATED
f	FULLBODY	INTERMEDIATE	BARBELL	Thrusters with barbell	PRONATED
f	UPPER	INTERMEDIATE	PULLUP_BAR	Pull-Ups	PRONATED
f	FULLBODY	ADVANCED	BARBELL	Clean and jerk	PRONATED
f	LOWER	INTERMEDIATE	PLYO_BOX	Box jump	\N
f	FULLBODY	ADVANCED	BARBELL	Snatch	PRONATED
t	UPPER	BEGINNER	PLATE	Russian twist (Weighted)	\N
f	UPPER	INTERMEDIATE	PLATE	Incline bench crunches (Weighted)	\N
f	UPPER	BEGINNER	\N	Incline bench crunches	\N
t	UPPER	BEGINNER	DUMBBELL	Dumbbell side bends	\N
t	UPPER	BEGINNER	\N	Scissor abs	\N
f	UPPER	INTERMEDIATE	ABDOMINAL_WHEEL	Abdominal wheel	\N
t	UPPER	BEGINNER	\N	Bicycle crunches	\N
t	UPPER	BEGINNER	\N	Bicycle crunches with raised legs	\N
t	UPPER	INTERMEDIATE	MACHINE	Cable core palloff press	\N
f	UPPER	BEGINNER	CABLE	Cable crunches	\N
t	UPPER	BEGINNER	CABLE	Cable twist (Up to down)	\N
t	UPPER	BEGINNER	CABLE	Cable twist (Down to up)	\N
f	UPPER	BEGINNER	\N	Seated crunches	\N
f	UPPER	INTERMEDIATE	PLATE	Seated crunches (Weighted)	\N
f	UPPER	BEGINNER	MACHINE	Seated crunches (Machine)	\N
t	UPPER	BEGINNER	\N	Dead bug	\N
t	UPPER	ADVANCED	\N	Dragon flag	\N
t	UPPER	BEGINNER	\N	Knees to elbows	\N
t	UPPER	INTERMEDIATE	\N	Leg flapping	\N
f	UPPER	INTERMEDIATE	BAR	Knee raises	\N
f	UPPER	ADVANCED	BAR	Leg raises	\N
t	UPPER	BEGINNER	\N	Heel touch	\N
f	UPPER	INTERMEDIATE	\N	Hollow rock	\N
f	UPPER	INTERMEDIATE	BOX	Jack knife	\N
f	UPPER	INTERMEDIATE	\N	V-ups	\N
f	UPPER	INTERMEDIATE	PARALLEL_BARS	Knees to chest	\N
f	UPPER	ADVANCED	PARALLEL_BARS	Sustained L-sit	\N
f	UPPER	INTERMEDIATE	PARALLEL_BARS	L-sit	\N
t	UPPER	INTERMEDIATE	BARBELL	Landmine 180	\N
t	UPPER	BEGINNER	\N	Oblique abdominal	\N
f	UPPER	BEGINNER	\N	Plank	\N
f	UPPER	BEGINNER	PLATE	Weighted plank	\N
f	LOWER	BEGINNER	MACHINE	Hip abduction	\N
f	LOWER	BEGINNER	MACHINE	Hip adduction	\N
f	UPPER	BEGINNER	BAR	Behind the back bicep curl	\N
f	UPPER	BEGINNER	BAR	Palms-up wrist curl	SUPINATED
f	UPPER	BEGINNER	BAR	Palms-down wrist curl	PRONATED
f	UPPER	INTERMEDIATE	PLATE	Forearm roller	PRONATED
f	UPPER	BEGINNER	BAR	Bar bicep curl	SUPINATED
t	UPPER	BEGINNER	CABLE	Cable bicep curl	SUPINATED
t	UPPER	BEGINNER	DUMBBELL	Dumbbell bicep curl	SUPINATED
f	UPPER	BEGINNER	MACHINE	Machine bicep curl	SUPINATED
t	UPPER	BEGINNER	CABLE	Cable hammer bicep curl	NEUTRAL
t	UPPER	BEGINNER	DUMBBELL	Dumbbell hammer bicep curl	NEUTRAL
f	UPPER	BEGINNER	MACHINE	Machine hammer bicep curl	NEUTRAL
f	UPPER	BEGINNER	Z_BAR	Z-bar hammer bicep curl	NEUTRAL
f	UPPER	INTERMEDIATE	BARBELL	Preacher curl (Barbell)	SUPINATED
t	UPPER	INTERMEDIATE	DUMBBELL	Preacher curl (Dumbbell)	SUPINATED
f	UPPER	INTERMEDIATE	MACHINE	Preacher curl (Machine)	SUPINATED
t	UPPER	INTERMEDIATE	DUMBBELL	Spider curl (Dumbbell)	SUPINATED
f	UPPER	INTERMEDIATE	BARBELL	Spider curl (Barbell)	SUPINATED
t	UPPER	INTERMEDIATE	DUMBBELL	Incline bench bicep curl	SUPINATED
t	UPPER	INTERMEDIATE	CABLE	Overhead curl	SUPINATED
t	UPPER	BEGINNER	CABLE	Plate curl	SUPINATED
f	UPPER	INTERMEDIATE	BARBELL	Inverted curl	SUPINATED
t	UPPER	INTERMEDIATE	DUMBBELL	Inverted curl (Dumbbell)	SUPINATED
t	UPPER	INTERMEDIATE	DUMBBELL	Concentration curl	SUPINATED
f	FULLBODY	INTERMEDIATE	AIR_BIKE	Air bike	\N
f	FULLBODY	INTERMEDIATE	STAIRS_MACHINE	Stairs machine	\N
f	FULLBODY	BEGINNER	TREADMILL	Running	\N
f	FULLBODY	BEGINNER	STATIONARY_BIKE	Stationary bike	\N
t	LOWER	INTERMEDIATE	\N	Assisted pistol squat	\N
t	LOWER	ADVANCED	\N	Pistol squat	\N
t	LOWER	ADVANCED	PLATE	Weighted pistol squat	\N
t	LOWER	INTERMEDIATE	DUMBBELL	Bulgarian split squat	\N
t	LOWER	INTERMEDIATE	DUMBBELL	Dumbbell lunge	\N
t	LOWER	INTERMEDIATE	DUMBBELL	Dumbbell step	\N
f	LOWER	INTERMEDIATE	BARBELL	Barbell front squat	\N
f	LOWER	INTERMEDIATE	DUMBBELL	Dumbbell front squat	\N
f	LOWER	BEGINNER	DUMBBELL	Goblet squat	\N
f	LOWER	INTERMEDIATE	HACK_SQUAT	Hack squat	\N
f	LOWER	BEGINNER	\N	Jump squat	\N
t	LOWER	BEGINNER	\N	Lateral lunge	\N
t	LOWER	BEGINNER	\N	Lunges	\N
f	LOWER	INTERMEDIATE	PENDULUM_SQUAT	Pendulum squat	\N
f	LOWER	BEGINNER	LEG_EXTENSION_MACHINE	Leg extension	\N
f	LOWER	BEGINNER	LEG_PRESS	Leg press	\N
f	LOWER	BEGINNER	LEG_PRESS_MACHINE	Seated leg press	\N
f	LOWER	BEGINNER	BARBELL	Paused barbell back squat	\N
t	LOWER	INTERMEDIATE	BARBELL	Barbell inverted lunges	\N
t	LOWER	INTERMEDIATE	\N	Inverted lunges	\N
t	LOWER	BEGINNER	LEG_EXTENSION_MACHINE	Unilateral leg extension	\N
f	LOWER	ADVANCED	BENCH	Sissy squat	\N
f	LOWER	BEGINNER	SMITH_MACHINE	Back squat (Smith machine)	\N
f	LOWER	BEGINNER	SMITH_MACHINE	Front squat (Smith machine)	\N
t	LOWER	BEGINNER	BOX	Step up	\N
f	LOWER	BEGINNER	\N	Sumo squat	\N
f	LOWER	BEGINNER	BARBELL	Barbell sumo squat	\N
f	LOWER	BEGINNER	DUMBBELL	Dumbbell sumo squat	\N
f	LOWER	BEGINNER	\N	Wall sit	\N
f	LOWER	INTERMEDIATE	BARBELL	Zercher squat	\N
f	UPPER	BEGINNER	\N	Lying neck curls	\N
f	UPPER	BEGINNER	\N	Lying neck extensions	\N
f	UPPER	BEGINNER	PLATE	Lying neck curls (Weighted)	\N
f	UPPER	BEGINNER	PLATE	Lying neck extensions (Weighted)	\N
f	UPPER	BEGINNER	LAT_PULLDOWN_MACHINE	Lat pulldowns (Machine)	PRONATED
f	UPPER	BEGINNER	LAT_PULLDOWN_MACHINE	Chin ups (Machine)	SUPINATED
f	UPPER	BEGINNER	LAT_PULLDOWN_MACHINE	Neutral pulldowns (Machine)	NEUTRAL
f	UPPER	INTERMEDIATE	LAT_PULLDOWN_MACHINE	Wide lat pulldowns (Machine)	PRONATED
f	UPPER	BEGINNER	PULLUP_BAR	Chin ups	SUPINATED
t	UPPER	BEGINNER	DUMBBELL	Dumbbell row (Unilateral)	NEUTRAL
f	UPPER	BEGINNER	ASSISTED_PULLUP_MACHINE	Assisted pull-ups	PRONATED
f	UPPER	BEGINNER	ASSISTED_PULLUP_MACHINE	Assisted chin-ups	SUPINATED
t	UPPER	INTERMEDIATE	ISO_LATERAL_ROW_MACHINE	High row (Iso-lateral)	NEUTRAL
f	UPPER	INTERMEDIATE	PULLUP_BAR	Negative pull-ups	PRONATED
f	UPPER	BEGINNER	PULLUP_BAR	Band-assisted pull-ups	PRONATED
f	UPPER	ADVANCED	PULLUP_BAR	Weighted pull-ups	PRONATED
f	UPPER	INTERMEDIATE	CABLE	Cable pullover	NEUTRAL
f	UPPER	BEGINNER	PULLOVER_MACHINE	Machine pullover	NEUTRAL
t	UPPER	INTERMEDIATE	CABLE	Cable pullover (Unilateral)	NEUTRAL
f	LOWER	BEGINNER	BACK_EXTENSION_MACHINE	Back extension (Machine)	\N
f	LOWER	INTERMEDIATE	PLATE	Back extension (Weighted)	\N
f	LOWER	BEGINNER	\N	Superman holds	\N
f	LOWER	BEGINNER	\N	Superman	\N
f	UPPER	BEGINNER	SEATED_ROW_MACHINE	Seated row (Machine, wide grip)	PRONATED
f	UPPER	BEGINNER	CABLE	Seated cable row	NEUTRAL
f	UPPER	INTERMEDIATE	BARBELL	Bent-over barbell row	PRONATED
f	UPPER	INTERMEDIATE	DUMBBELL	Bent-over dumbbell row	NEUTRAL
f	UPPER	INTERMEDIATE	DUMBBELL	Chest-supported dumbbell row	NEUTRAL
f	UPPER	BEGINNER	PULLUP_BAR	Dead hang	PRONATED
t	UPPER	INTERMEDIATE	DUMBBELL	Gorilla row (Dumbbells)	NEUTRAL
f	UPPER	BEGINNER	BARBELL	Inverted row	SUPINATED
t	UPPER	INTERMEDIATE	ISO_LATERAL_ROW_MACHINE	Low row (Iso-lateral)	NEUTRAL
f	UPPER	INTERMEDIATE	BARBELL	Landmine row (T-bar row)	NEUTRAL
f	LOWER	INTERMEDIATE	BARBELL	Rack pull	NEUTRAL
f	UPPER	BEGINNER	PULLUP_BAR	Scapular pull-ups	PRONATED
f	UPPER	BEGINNER	CABLE	Seated cable row (Wide grip)	PRONATED
f	UPPER	BEGINNER	CABLE	Seated cable row (V-grip)	NEUTRAL
f	UPPER	INTERMEDIATE	T_BAR	T-bar row	NEUTRAL
f	LOWER	BEGINNER	CALF_RAISE_MACHINE	Calf extension (Machine)	\N
f	LOWER	BEGINNER	LEG_PRESS_MACHINE	Calf press (Leg press machine)	\N
f	LOWER	BEGINNER	SEATED_CALF_RAISE_MACHINE	Seated calf raise	\N
t	LOWER	BEGINNER	\N	Single-leg calf raise	\N
t	LOWER	INTERMEDIATE	BARBELL	Single-leg calf raise (Barbell)	\N
t	LOWER	BEGINNER	DUMBBELL	Single-leg calf raise (Dumbbell)	\N
f	LOWER	BEGINNER	SMITH_MACHINE	Standing calf raise (Smith machine)	\N
f	LOWER	BEGINNER	DUMBBELL	Standing calf raise (Dumbbells)	\N
t	LOWER	BEGINNER	CALF_RAISE_MACHINE	Single-leg calf raise (Machine)	\N
f	LOWER	INTERMEDIATE	BARBELL	Sumo deadlift	MIXED
t	LOWER	BEGINNER	CABLE	Cable glute kickback	\N
t	LOWER	BEGINNER	GLUTE_KICKBACK_MACHINE	Glute kickback (Machine)	\N
t	LOWER	INTERMEDIATE	DUMBBELL	Single-leg hip thrust (Dumbbell)	\N
f	LOWER	INTERMEDIATE	REVERSE_HYPEREXTENSION_MACHINE	Reverse hyperextension (Glute focus)	\N
t	LOWER	BEGINNER	CABLE	Cable lateral leg raise	\N
f	LOWER	BEGINNER	RESISTANCE_BAND	Lateral band walk	\N
f	LOWER	INTERMEDIATE	BARBELL	Barbell hip thrust	\N
f	LOWER	BEGINNER	BENCH	Bodyweight hip thrust	\N
t	LOWER	BEGINNER	\N	Donkey kick (Bodyweight)	\N
f	LOWER	BEGINNER	DUMBBELL	Frog pumps (Dumbbell)	\N
f	LOWER	INTERMEDIATE	TRAP_BAR	Trap bar deadlift	NEUTRAL
f	LOWER	BEGINNER	SMITH_MACHINE	Smith machine deadlift	PRONATED
f	LOWER	BEGINNER	DUMBBELL	Dumbbell deadlift	NEUTRAL
f	LOWER	INTERMEDIATE	CABLE	Cable pull-through	NEUTRAL
f	UPPER	BEGINNER	BARBELL	Barbell shoulder press	PRONATED
f	UPPER	BEGINNER	SHOULDER_PRESS_MACHINE	Machine shoulder press	NEUTRAL
f	UPPER	INTERMEDIATE	DUMBBELL	Arnold press (Dumbbells)	SUPINATED_TO_PRONATED
f	UPPER	BEGINNER	DUMBBELL	Rear delt fly (Dumbbells)	NEUTRAL
f	UPPER	BEGINNER	REAR_DELT_MACHINE	Rear delt fly (Machine)	NEUTRAL
f	UPPER	BEGINNER	CABLE	Face pull	NEUTRAL
f	UPPER	BEGINNER	CABLE	Front raise (Cable)	PRONATED
f	UPPER	BEGINNER	DUMBBELL	Front raise (Dumbbells)	PRONATED
t	UPPER	BEGINNER	CABLE	Lateral raise (Cable)	NEUTRAL
f	UPPER	BEGINNER	DUMBBELL	Lateral raise (Dumbbells)	NEUTRAL
f	UPPER	BEGINNER	LATERAL_RAISE_MACHINE	Lateral raise (Machine)	\N
f	UPPER	INTERMEDIATE	\N	Pike push-up	\N
f	UPPER	BEGINNER	SMITH_MACHINE	Military press (Smith machine)	PRONATED
f	LOWER	INTERMEDIATE	BARBELL	Romanian deadlift (Barbell)	MIXED
f	LOWER	INTERMEDIATE	DUMBBELL	Romanian deadlift (Dumbbells)	NEUTRAL
f	LOWER	BEGINNER	LEG_CURL_MACHINE	Lying leg curl (Machine)	\N
f	LOWER	ADVANCED	\N	Nordic hamstring curl	\N
t	LOWER	INTERMEDIATE	DUMBBELL	Single-leg Romanian deadlift (Dumbbell)	NEUTRAL
f	LOWER	BEGINNER	SEATED_LEG_CURL_MACHINE	Seated leg curl (Machine)	\N
f	UPPER	BEGINNER	DUMBBELL	Incline bench press (Dumbbells)	NEUTRAL
f	UPPER	BEGINNER	BARBELL	Incline bench press (Barbell)	PRONATED
f	UPPER	BEGINNER	DUMBBELL	Flat bench press (Dumbbells)	NEUTRAL
f	UPPER	BEGINNER	PEC_DECK	Chest fly (Machine)	NEUTRAL
f	UPPER	BEGINNER	DUMBBELL	Chest fly (Dumbbells)	NEUTRAL
f	UPPER	BEGINNER	INCLINE_CHEST_PRESS_MACHINE	Incline bench press (Machine)	NEUTRAL
f	UPPER	BEGINNER	CHEST_PRESS_MACHINE	Flat bench press (Machine)	NEUTRAL
f	UPPER	BEGINNER	\N	Push-ups (Bodyweight)	\N
f	UPPER	INTERMEDIATE	BARBELL	Flat bench press (Wide grip, Barbell)	WIDE_PRONATED
f	UPPER	BEGINNER	SMITH_MACHINE	Flat bench press (Smith machine)	PRONATED
f	UPPER	BEGINNER	SMITH_MACHINE	Incline bench press (Smith machine)	PRONATED
f	UPPER	INTERMEDIATE	CABLE	Cable chest fly (Crossover)	NEUTRAL
f	UPPER	INTERMEDIATE	PARALLEL_BARS	Parallel bar dips (Bodyweight)	NEUTRAL
f	UPPER	ADVANCED	PARALLEL_BARS	Weighted dips (Parallel bars)	NEUTRAL
f	UPPER	INTERMEDIATE	BARBELL	Decline bench press (Barbell)	PRONATED
f	UPPER	INTERMEDIATE	DUMBBELL	Decline bench press (Dumbbells)	NEUTRAL
f	UPPER	BEGINNER	DUMBBELL	Hex press (Single Dumbbell)	NEUTRAL
f	UPPER	BEGINNER	BOX	Incline push-ups (Box or Bench)	\N
t	UPPER	ADVANCED	\N	One-arm push-up	\N
t	UPPER	INTERMEDIATE	CABLE	Unilateral cable chest fly	NEUTRAL
f	UPPER	INTERMEDIATE	PLATE	Weighted push-ups (Floor)	\N
f	UPPER	BEGINNER	BARBELL	Barbell shrugs	PRONATED
f	UPPER	BEGINNER	DUMBBELL	Dumbbell shrugs	NEUTRAL
f	UPPER	INTERMEDIATE	CABLE	Cable shrugs	NEUTRAL
f	UPPER	BEGINNER	SHRUG_MACHINE	Machine shrugs	NEUTRAL
f	UPPER	INTERMEDIATE	SMITH_MACHINE	Smith machine shrugs	PRONATED
f	UPPER	BEGINNER	BENCH	Bench dips (Bodyweight)	NEUTRAL
f	UPPER	INTERMEDIATE	BARBELL	Close-grip bench press (Barbell)	CLOSE_PRONATED
f	UPPER	BEGINNER	\N	Diamond push-ups	\N
f	UPPER	BEGINNER	\N	Triceps floor dips	NEUTRAL
f	UPPER	INTERMEDIATE	BARBELL	JM press (Barbell)	PRONATED
f	UPPER	BEGINNER	DIP_MACHINE	Machine dips	NEUTRAL
t	UPPER	BEGINNER	DUMBBELL	One-arm dumbbell triceps extension	NEUTRAL
t	UPPER	BEGINNER	CABLE	One-arm cable triceps extension	NEUTRAL
f	UPPER	INTERMEDIATE	BARBELL	French press (Barbell)	PRONATED
f	UPPER	INTERMEDIATE	DUMBBELL	French press (Dumbbell)	NEUTRAL
f	UPPER	BEGINNER	ASSISTED_DIP_MACHINE	Assisted machine dips (Triceps)	NEUTRAL
f	UPPER	INTERMEDIATE	BARBELL	Overhead triceps extension (Barbell)	PRONATED
t	UPPER	INTERMEDIATE	CABLE	One-arm overhead triceps extension (Cable)	NEUTRAL
f	UPPER	BEGINNER	TRICEPS_EXTENSION_MACHINE	Triceps extension (Machine)	NEUTRAL
t	UPPER	BEGINNER	CABLE	Triceps kickback (Cable)	NEUTRAL
t	UPPER	BEGINNER	DUMBBELL	Triceps kickback (Dumbbell)	NEUTRAL
f	UPPER	BEGINNER	CABLE	Two-hand cable triceps pushdown	PRONATED
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

