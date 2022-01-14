CREATE TABLE events
(
    id               SERIAL PRIMARY KEY NOT NULL,
    name             character varying(255),
    amount           integer,
    price            integer,
    start_date       timestamp,
    created_at       timestamp,
    updated_at       timestamp
)