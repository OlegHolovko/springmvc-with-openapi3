CREATE TABLE orders
(
    id          SERIAL PRIMARY KEY NOT NULL,
    buyer_name  character varying(255),
    amount      integer,
    total_price integer,
    event_id    bigint,
    created_at  timestamp without time zone,
    updated_at  timestamp without time zone,
    FOREIGN KEY (event_id) REFERENCES events (id)
)
