CREATE SEQUENCE role_id_seq;
CREATE SEQUENCE user_id_seq;

CREATE TABLE role
(
	role_id integer NOT NULL DEFAULT nextval('role_id_seq'::regclass),
	role_name character varying(10) NOT NULL UNIQUE,
	CONSTRAINT role_pkey PRIMARY KEY (role_id)
);

CREATE TABLE public.user
(
	user_id bigint NOT NULL DEFAULT nextval('user_id_seq'::regclass),
	username character varying(25) NOT NULL UNIQUE,
	password character varying(25) NOT NULL,
	role_id integer NOT NULL,
	CONSTRAINT user_pkey PRIMARY KEY (user_id),
    CONSTRAINT role_fkey FOREIGN KEY (role_id)
        REFERENCES role (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE student
    ADD COLUMN user_id bigint UNIQUE;

ALTER TABLE student
    ADD FOREIGN KEY (user_id)
    REFERENCES public.user (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
