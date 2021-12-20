DELETE FROM orders;
DELETE FROM events;

alter sequence events_id_seq restart;
alter sequence orders_id_seq restart;

INSERT INTO events (name, amount, price, start_date, created_at) VALUES ('Football', 100, 1257, '2022-01-13', '2021-12-14');
INSERT INTO events (name, amount, price, start_date, created_at) VALUES ('Concert', 20, 56, '2021-12-31', '2021-12-14');
INSERT INTO events (name, amount, price, start_date, created_at) VALUES ('Opera', 70, 2000, '2022-05-01', '2021-12-14');

INSERT INTO orders (buyer_name, amount, total_price, event_id, created_at) VALUES ('Peter', 1, 2000, (SELECT  id FROM events WHERE name = 'Opera'), '2021-12-14');
INSERT INTO orders (buyer_name, amount, total_price, event_id, created_at) VALUES ('John', 2, 4000, (SELECT  id FROM events WHERE name = 'Opera'), '2021-12-14');
INSERT INTO orders (buyer_name, amount, total_price, event_id, created_at) VALUES ('Ivan', 1, 1257, (SELECT  id FROM events WHERE name = 'Football'), '2021-12-14');
INSERT INTO orders (buyer_name, amount, total_price, event_id, created_at) VALUES ('Julia', 1, 1257, (SELECT  id FROM events WHERE name = 'Football'), '2021-12-14');
