# playGames schema

# --- !Ups

CREATE SEQUENCE play_game_id_seq;
CREATE TABLE play_game (
    id integer NOT NULL DEFAULT nextval('play_game_id_seq'),
    title varchar(255)
);

# --- !Downs

DROP TABLE play_game;
DROP SEQUENCE play_game_id_seq;