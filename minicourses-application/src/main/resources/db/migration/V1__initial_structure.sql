----------------------------- CREATE SEQUENCES -------------------------
CREATE SEQUENCE public.permission_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE SEQUENCE public.mini_course_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE SEQUENCE public.user_account_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

----------------------------- CREATE TABLES -------------------------

CREATE TABLE public.role (
 id BIGINT NOT NULL PRIMARY KEY,
 name VARCHAR(50) NOT NULL,
 description VARCHAR(250) NULL
);

CREATE TABLE public.permission
(
    id bigint NOT NULL DEFAULT nextval('permission_seq'),
    description character varying(255),
    name character varying(255),
    CONSTRAINT permission_pkey PRIMARY KEY (id)
);

CREATE TABLE public.role_permission (
    id_role BIGINT NOT NULL ,
    id_permission BIGINT NOT NULL,
    PRIMARY KEY (id_role, id_permission),
    CONSTRAINT role_permission_role_FK FOREIGN KEY (id_role) REFERENCES public.role (id),
    CONSTRAINT role_permission_permission_FK FOREIGN KEY (id_permission) REFERENCES public.permission (id)
);

CREATE TABLE public.user_account
(
    type character varying(31) NOT NULL,
    id bigint NOT NULL DEFAULT nextval('user_account_seq'),
    deleted boolean,
    email character varying(255) NOT NULL,
    id_role bigint,
    last_login_time bigint,
    name character varying(255),
    password character varying(255),
    registration character varying(255),
    birthday timestamp without time zone,
    cpf character varying(255),
    CONSTRAINT user_account_pkey PRIMARY KEY (id),
    CONSTRAINT user_account_cpf_UK UNIQUE (cpf),
    CONSTRAINT user_account_registration_UK UNIQUE (registration),
    CONSTRAINT user_account_email_uk UNIQUE (email),
    CONSTRAINT user_account_role_fk FOREIGN KEY (id_role)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.mini_course
(
    id bigint NOT NULL DEFAULT nextval('mini_course_seq'),
    duration bigint,
    name character varying(255),
    start_date timestamp without time zone,
    vacancies_number integer,
    professor_owner_id bigint NOT NULL,
    deleted boolean,
    CONSTRAINT mini_course_pkey PRIMARY KEY (id),
    CONSTRAINT professor_owner_FK FOREIGN KEY (professor_owner_id)
        REFERENCES public.user_account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.mini_course_participants
(
    mini_course_id bigint NOT NULL,
    participants_id bigint NOT NULL,
    CONSTRAINT mini_course_participants_fk FOREIGN KEY (mini_course_id)
        REFERENCES public.mini_course (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT student_participants_fk FOREIGN KEY (participants_id)
        REFERENCES public.user_account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.professor_phones
(
    professor_mapped_id bigint NOT NULL,
    phones character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT professor_owner_fk FOREIGN KEY (professor_mapped_id)
        REFERENCES public.user_account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


    ---------- Inserts roles to the system ----------
INSERT INTO public.role (id, name, description)
VALUES (1, 'Super User', 'Role for a super user that allows all operations in the system.');

INSERT INTO public.role (id, name, description)
VALUES (2, 'Professor', 'Role for professors in the system.');

INSERT INTO public.role (id, name, description)
VALUES (3, 'Student', 'Role for students in the system.');

-- INSERT ALL PERMISSIONS FOR SUPER USER --
INSERT INTO public.permission (name, description) VALUES ('read-*', 'Read all items.');
INSERT INTO public.role_permission (id_role, id_permission) VALUES (1, currval('permission_seq'));

INSERT INTO public.permission (name, description) VALUES ('insert-*', 'Insert any item.');
INSERT INTO public.role_permission (id_role, id_permission) VALUES (1, currval('permission_seq'));

INSERT INTO public.permission (name, description) VALUES ('update-*', 'Update any item.');
INSERT INTO public.role_permission (id_role, id_permission) VALUES (1, currval('permission_seq'));

INSERT INTO public.permission (name, description) VALUES ('delete-*', 'Delete any item.');
INSERT INTO public.role_permission (id_role, id_permission) VALUES (1, currval('permission_seq'));

------- INSERT ALL PERMISSION FOR PROFESSOR
INSERT INTO public.permission (name, description) VALUES ('read-professors', 'Read all or one professor.');
INSERT INTO public.role_permission (id_role, id_permission) VALUES (1, currval('permission_seq'));

INSERT INTO public.permission (name, description) VALUES ('insert-minicourse*', 'Insert one minicourse.');
INSERT INTO public.role_permission (id_role, id_permission) VALUES (1, currval('permission_seq'));

------- INSERT ALL PERMISSION FOR STUDENT
INSERT INTO public.permission (name, description) VALUES ('read-students*', 'Read all of one students.');
INSERT INTO public.role_permission (id_role, id_permission) VALUES (1, currval('permission_seq'));

INSERT INTO public.permission (name, description) VALUES ('subscribe-minicourse*', 'Subscribe in one minicourse.');
INSERT INTO public.role_permission (id_role, id_permission) VALUES (1, currval('permission_seq'));

------- Creating ADMIN USER --------
INSERT INTO public.user_account(
    type, deleted, email, id_role, last_login_time, name, password,
                                registration, birthday, cpf)
VALUES ('default', false, 'admin@admin',1, null, 'Administrator', '4f26aeafdb2367620a393c973eddbe8f8b846ebd',
        null, null, null);





