# rouletteList schema

# --- !Ups

CREATE SEQUENCE roulette_list_id_seq;
CREATE TABLE roulette_list (
    id integer NOT NULL DEFAULT nextval('roulette_list_id_seq'),
    roulette_number integer,
    play_game_id integer,
    game_count integer
);

# --- !Downs

DROP TABLE roulette_list;
DROP SEQUENCE roulette_list_id_seq;