DROP TABLE auction_parameter CASCADE;

CREATE TABLE auction_parameter(
    parameter_id    INT NOT NULL,
    auction_id      INT NOT NULL,
    value           VARCHAR NOT NULL,
    CONSTRAINT parameter_fk FOREIGN KEY (parameter_id) REFERENCES parameter (id),
    CONSTRAINT auction_fk FOREIGN KEY (auction_id) REFERENCES auction (id)
);