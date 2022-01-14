CREATE TABLE events
(
    id               SERIAL PRIMARY KEY NOT NULL,
    name             character varying(255),
    amount           integer,
    price            integer,
    start_date       timestamp without time zone,
    created_at       timestamp without time zone,
    updated_at       timestamp without time zone
)