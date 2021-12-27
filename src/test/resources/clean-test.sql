alter sequence events_id_seq restart;
alter sequence orders_id_seq restart;

DELETE FROM orders;
DELETE FROM events;