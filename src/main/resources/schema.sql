CREATE TABLE IF NOT EXISTS USERDATA (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username varchar(64) NOT NULL,
    nome varchar(64 ) NOT NULL,
    cognome varchar(64) NOT NULL,
    email varchar(256) NOT NULL,
    birthdate date NOT NULL,
    sport varchar(64) NOT NULL,
    squadra varchar(64) NOT NULL
    );

CREATE TABLE IF NOT EXISTS USERS (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username varchar(64) NOT NULL,
    password varchar(256) NOT NULL,
    enabled INT NOT NULL
    );

CREATE TABLE IF NOT EXISTS AUTHORITIES (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username varchar(64) NOT NULL,
    authority varchar(64) NOT NULL
    );
CREATE TABLE IF NOT EXISTS GIORNATE (
    id int not null,
    giorno varchar(64) NOT NULL,
    punti int NOT NULL,
    primary key (id, giorno)
    );
-- INSERT into USERS (username, password, enabled)
-- SELECT * FROM(
--     values('admin#21','password',1),
--           ('moderator#21','password',1),
--           ('user#21','passworddainserirre',1)
--     ) as utenti(username,password,enabled)
-- WHERE NOT EXISTS(SELECT 1 FROM USERS LIMIT 1);

CREATE TABLE IF NOT EXISTS RECENSIONI
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    userId  BIGINT NOT NULL,
    voto       int NOT NULL,
    commento   varchar(256) NOT NULL


);
CREATE TABLE IF NOT EXISTS PREMI
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    USERNAME  varchar(64) NOT NULL,
    premio   varchar(256) NOT NULL
)

