----------------------------- CREATE SEQUENCES -------------------------
CREATE SEQUENCE public.permission_seq
    INCREMENT 1
    START 18
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;


----------------------------- CREATE TABLES -------------------------

CREATE TABLE public.role (
 id BIGINT NOT NULL PRIMARY KEY,
 name VARCHAR(50) NOT NULL,
 description VARCHAR(250) NULL
);

CREATE TABLE public.permission (
    id BIGINT NOT NULL DEFAULT nextval('permission_seq'),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(250) NULL,
    CONSTRAINT permission_pkey PRIMARY KEY (id)
);

CREATE TABLE public.role_permission (
    id_role BIGINT NOT NULL ,
    id_permission BIGINT NOT NULL,
    PRIMARY KEY (id_role, id_permission),
    CONSTRAINT role_permission_role_FK FOREIGN KEY (id_role) REFERENCES public.role (id),
    CONSTRAINT role_permission_permission_FK FOREIGN KEY (id_permission) REFERENCES public.permission (id)
);

ALTER TABLE user_account
ADD CONSTRAINT user_role_FK FOREIGN KEY(id_role) REFERENCES public.role(id);


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



