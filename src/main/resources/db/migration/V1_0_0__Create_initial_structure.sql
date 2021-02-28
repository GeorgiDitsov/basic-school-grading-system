CREATE SEQUENCE student_id_seq;
CREATE SEQUENCE course_id_seq;
CREATE SEQUENCE student_course_id_seq;
CREATE SEQUENCE mark_id_seq;

CREATE TABLE student
(
	student_id bigint NOT NULL DEFAULT nextval('student_id_seq'::regclass),
    student_name character varying(50) NOT NULL,
    CONSTRAINT student_pkey PRIMARY KEY (student_id)
);

CREATE TABLE course
(
    course_id bigint NOT NULL DEFAULT nextval('course_id_seq'::regclass),
    course_name character varying(20) NOT NULL,
    CONSTRAINT course_pkey PRIMARY KEY (course_id)
);

CREATE TABLE student_course
(
    student_course_id bigint NOT NULL DEFAULT nextval('student_course_id_seq'::regclass),
    student_id bigint NOT NULL,
    course_id bigint NOT NULL,
    CONSTRAINT student_course_pkey PRIMARY KEY (student_course_id),
    CONSTRAINT course_fkey FOREIGN KEY (course_id)
        REFERENCES course (course_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT student_fkey FOREIGN KEY (student_id)
        REFERENCES student (student_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE mark
(
    mark_id bigint NOT NULL DEFAULT nextval('mark_id_seq'::regclass),
    mark real NOT NULL,
    mark_date timestamp without time zone NOT NULL,
    student_course_id bigint,
    CONSTRAINT mark_pkey PRIMARY KEY (mark_id),
    CONSTRAINT student_course_fkey FOREIGN KEY (student_course_id)
        REFERENCES student_course (student_course_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
