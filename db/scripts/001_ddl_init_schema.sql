create TABLE IF NOT EXISTS post (
    id          SERIAL PRIMARY KEY,
    name        TEXT,
    description TEXT,
    created     TIMESTAMP,
    visible     BOOLEAN,
    city_id     INTEGER
);

create TABLE IF NOT EXISTS candidate (
    id          SERIAL PRIMARY KEY,
    name        TEXT,
    description TEXT,
    created     TIMESTAMP,
    photo       BYTEA
);

create TABLE IF NOT EXISTS users (
  id            SERIAL PRIMARY KEY,
  name     VARCHAR(50),
  email    VARCHAR(50) UNIQUE,
  password TEXT
);