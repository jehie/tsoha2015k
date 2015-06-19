CREATE TABLE Tavara(
  id SERIAL PRIMARY KEY,
  valmistaja_id INTEGER REFERENCES TavaranValmistaja(id), 
  nimi varchar(50) NOT NULL,
  hinta int NOT NULL,
  saatavilla boolean DEFAULT TRUE,
  varastossa int NOT NULL,
  kuvaus varchar(400),
  julkaistu DATE,
  added DATE
);

CREATE TABLE TavaranValmistaja(
  id SERIAL PRIMARY KEY,
  nimi varchar(50) NOT NULL,
  maa varchar(400),
  added DATE
);

CREATE TABLE Ateria(
  id SERIAL PRIMARY KEY,
  nimi varchar(50) NOT NULL,
  hinta int NOT NULL,
  saatavilla boolean DEFAULT TRUE,
  kuvaus varchar(400),
  lisatty DATE
);

CREATE TABLE Lento(
  id SERIAL PRIMARY KEY,
  tunnus varchar(50) NOT NULL,
);

CREATE TABLE Kayttaja(
  id SERIAL PRIMARY KEY,
  email varchar(50) NOT NULL,
  admin boolean DEFAULT false
);

CREATE TABLE Tilaus(
  id SERIAL PRIMARY KEY,
  tilaaja_id INTEGER REFERENCES Kayttaja(id), 
  toimitettu boolean DEFAULT false,
  ateria_id INTEGER REFERENCES Ateria(id),
  yhteishinta int,
  lentoID INTEGER REFERENCES Lento(id),
  added DATE
);

CREATE TABLE Tilaustiedot(
  id SERIAL PRIMARY KEY,
  tilausID INTEGER REFERENCES tilaus(id), 
  tavaraID INTEGER REFERENCES tavara(id),
  kpl INTEGER
);


