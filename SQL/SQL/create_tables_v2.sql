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


CREATE TABLE Kayttaja(
  id SERIAL PRIMARY KEY,
  username varchar(20) NOT NULL,
  password varchar(20) NOT NULL,
  admin boolean DEFAULT false,
  maa varchar(20),
  added DATE
);

CREATE TABLE Tilaukset(
  id SERIAL PRIMARY KEY,
  tilaaja_id INTEGER REFERENCES Kayttaja(id), 
  toimitettu boolean DEFAULT false,
  hinta int,
  lentoID varchar(20),
  added DATE
);


