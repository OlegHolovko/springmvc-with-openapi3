CREATE TABLE orders
(
    id          SERIAL PRIMARY KEY NOT NULL,
    buyer_name  character varying(255),
    amount      integer,
    total_price integer,
    event_id    bigint,
    created_at  timestamp,
    updated_at  timestamp,
    FOREIGN KEY (event_id) REFERENCES events (id)
)
