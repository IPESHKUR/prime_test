CREATE TABLE IF NOT EXISTS reservation
(
    id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    full_name VARCHAR NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    phone_number VARCHAR NOT NULL,
    lot_id BIGINT NOT NULL,
    FOREIGN KEY (lot_id) REFERENCES lot(id)
    );