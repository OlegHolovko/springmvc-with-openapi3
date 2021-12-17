/*
INSERT INTO events (id, name, amount, price, start_date, created_at)
VALUES (nextval('event_id_seq'), 'Football', 100, 1257, '2022-01-13', '2021-12-14');
INSERT INTO events (id, name, amount, price, start_date, created_at)
VALUES (nextval('event_id_seq'), 'Concert', 20, 56, '2021-12-31', '2021-12-14');
INSERT INTO events (id, name, amount, price, start_date, created_at)
VALUES (nextval('event_id_seq'), 'Opera', 70, 2000, '2022-05-01', '2021-12-14');

INSERT INTO orders (buyer_name, amount, total_price, event_id, created_at) VALUES ('Peter', 1, 2000,3, '2021-12-14');
INSERT INTO orders (buyer_name, amount, total_price, event_id, created_at) VALUES ('John', 2, 4000,3, '2021-12-14');

INSERT INTO orders (buyer_name, amount, total_price, event_id, created_at) VALUES ('Ivan', 1, 1257,1, '2021-12-14');
INSERT INTO orders (buyer_name, amount, total_price, event_id, created_at) VALUES ('Julia', 1, 1257,1, '2021-12-14');

 */