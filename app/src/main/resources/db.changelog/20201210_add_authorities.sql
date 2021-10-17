CREATE SEQUENCE authority_id_seq;

CREATE TABLE  authority(
    id              integer         NOT NULL DEFAULT nextval('authority_id_seq'),
    user_id         integer         NOT NULL,
    authority       varchar(32)     NOT NULL
);