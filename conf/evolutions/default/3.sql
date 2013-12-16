# rouletteList schema

# --- !Ups

CREATE SEQUENCE number_id_seq;
CREATE TABLE number (
    id integer NOT NULL DEFAULT nextval('number_id_seq'),
    roulette_number integer,
    color integer,
    high_low integer,
    odd_even integer,
    column_bet integer,
    dozen_bet integer
);


INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (0, 99, 99, 99, 99, 99);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (1, 0, 0, 1, 2, 0);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (2, 1, 0, 0, 1, 0);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (3, 0, 0, 1, 0, 0);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (4, 1, 0, 0, 2, 0);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (5, 0, 0, 1, 1, 0);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (6, 1, 0, 0, 0, 0);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (7, 0, 0, 1, 2, 0);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (8, 1, 0, 0, 1, 0);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (9, 0, 0, 1, 0, 0);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (10, 1, 0, 0, 2, 0);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (11, 1, 0, 1, 1, 0);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (12, 0, 0, 0, 0, 0);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (13, 1, 0, 1, 2, 1);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (14, 0, 0, 0, 1, 1);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (15, 1, 0, 1, 0, 1);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (16, 0, 0, 0, 2, 1);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (17, 1, 0, 1, 1, 1);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (18, 0, 0, 0, 0, 1);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (19, 0, 1, 1, 2, 1);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (20, 1, 1, 0, 1, 1);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (21, 0, 1, 1, 0, 1);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (22, 1, 1, 0, 2, 1);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (23, 0, 1, 1, 1, 1);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (24, 1, 1, 0, 0, 1);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (25, 0, 1, 1, 2, 2);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (26, 1, 1, 0, 1, 2);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (27, 0, 1, 1, 0, 2);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (28, 1, 1, 0, 2, 2);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (29, 1, 1, 1, 1, 2);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (30, 0, 1, 0, 0, 2);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (31, 1, 1, 1, 2, 2);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (32, 0, 1, 0, 1, 2);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (33, 1, 1, 1, 0, 2);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (34, 0, 1, 0, 2, 2);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (35, 1, 1, 1, 1, 2);
INSERT INTO number (roulette_number, color, high_low, odd_even, column_bet, dozen_bet) VALUES (36, 0, 1, 0, 0, 2);

# --- !Downs

DROP TABLE roulette_list;
DROP SEQUENCE number_id_seq;