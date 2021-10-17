DROP TABLE test1;

CREATE TABLE users(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    username VARCHAR NOT NULL,
    passwordHash VARCHAR NOT NULL
);

CREATE TABLE authorities(
    id_authorities INT NOT NULL,
    users_id INT NOT NULL,
    authority VARCHAR NOT NULL,
    CONSTRAINT users_fk FOREIGN KEY (users_id) REFERENCES users(id)
);
