ALTER TABLE users
    ADD PRIMARY KEY (id);

CREATE SEQUENCE section_id_seq;

CREATE TABLE section(
    id              INT NOT NULL NOT NULL PRIMARY KEY DEFAULT nextval('section_id_seq'),
    name            VARCHAR NOT NULL
);

CREATE SEQUENCE category_id_seq;

CREATE TABLE category(
    id              INT NOT NULL PRIMARY KEY DEFAULT nextval('category_id_seq'),
    name            VARCHAR NOT NULL,
    section_id      INT NOT NULL,
    CONSTRAINT section_fk FOREIGN KEY (section_id) REFERENCES section (id)
);

CREATE SEQUENCE auction_id_seq;

CREATE TABLE auction(
    id              INT NOT NULL PRIMARY KEY DEFAULT nextval('auction_id_seq'),
    title           VARCHAR NOT NULL,
    description     VARCHAR NOT NULL,
    price           INT NOT NULL,
    creator_id      INT NOT NULL,
    CONSTRAINT users_fk FOREIGN KEY (creator_id) REFERENCES users (id)
);

CREATE SEQUENCE category_auction_id_seq;

CREATE TABLE category_auction(
    id              INT NOT NULL PRIMARY KEY DEFAULT nextval('category_auction_id_seq'),
    category_id     INT NOT NULL,
    auction_id      INT NOT NULL,
    CONSTRAINT category_fk FOREIGN KEY (category_id) REFERENCES category (id),
    CONSTRAINT auction_fk FOREIGN KEY (auction_id) REFERENCES auction (id)
);

CREATE SEQUENCE photo_id_seq;

CREATE TABLE photo(
    id              INT NOT NULL PRIMARY KEY DEFAULT nextval('photo_id_seq'),
    link            VARCHAR NOT NULL,
    auction_id      INT NOT NULL,
    CONSTRAINT auction_fk FOREIGN KEY (auction_id) REFERENCES auction (id)
);

CREATE SEQUENCE parameter_id_seq;

CREATE TABLE parameter(
    id              INT NOT NULL NOT NULL PRIMARY KEY DEFAULT nextval('parameter_id_seq'),
    key             VARCHAR NOT NULL,
    value           VARCHAR NOT NULL
);

CREATE SEQUENCE auction_parameter_id_seq;

CREATE TABLE auction_parameter(
    id              INT NOT NULL PRIMARY KEY DEFAULT nextval('auction_parameter_id_seq'),
    parameter_id    INT NOT NULL,
    auction_id      INT NOT NULL,
    CONSTRAINT parameter_fk FOREIGN KEY (parameter_id) REFERENCES parameter (id),
    CONSTRAINT auction_fk FOREIGN KEY (auction_id) REFERENCES auction (id)
);