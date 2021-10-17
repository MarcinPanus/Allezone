CREATE SEQUENCE users_id_seq;

CREATE TABLE users (
    id              integer         NOT NULL DEFAULT nextval('users_id_seq'),
    username        varchar(25)     NOT NULL,
    passwordHash    char(60)        NOT NULL
);