SET MODE Postgresql;

CREATE DATABASE wildlife_tracker;

\c wildlife_tracker;

CREATE  TABLE IF NOT EXISTS animals
id SERIAL PRIMARY KEY,
animalName VARCHAR,
type VARCHAR,
health VARCHAR,
age VARCHAR
);


