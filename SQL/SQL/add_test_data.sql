INSERT INTO tavaranvalmistaja(nimi, maa, added) VALUES ('Nokia Oy', 'Finland', now());
INSERT INTO tavaranvalmistaja(nimi, maa, added) VALUES ('Samsung', 'Etelä-Korea', now());
INSERT INTO tavaranvalmistaja(nimi, maa, added) VALUES ('Dove', 'USA', now());
INSERT INTO ateria( nimi, hinta, saatavilla, kuvaus, lisatty) VALUES ('Sipulikeutto', '25', true, 'Hieno sipulikeitto', now());

INSERT INTO Tavara (valmistaja_id, nimi, hinta, saatavilla, varastossa, kuvaus, julkaistu, added) VALUES (1, 'Nokia 3330', 250, true, 15, 'Great phone', now(), now());
INSERT INTO Tavara (valmistaja_id, nimi, hinta, saatavilla, varastossa, kuvaus, julkaistu, added) VALUES (2, 'Rolex 331250', 25055, true, 1, 'Great clock', now(), now());
INSERT INTO Tavara (valmistaja_id, nimi, hinta, saatavilla, varastossa, kuvaus, julkaistu, added) VALUES (1, 'Samsung Galaxy S5', 550, true, 45, 'New Samsung Phone', now(), now());

INSERT INTO kayttaja(email, admin) VALUES ('asdasd@asdasd.com, false);

INSERT INTO lento( tunnus) VALUES ('SIIPI-123');
INSERT INTO lento( tunnus) VALUES ('SIIPI-555');
INSERT INTO lento( tunnus) VALUES ('SIIPI-666');
INSERT INTO lento( tunnus) VALUES ('SIIPI-852');

INSERT INTO Tilaukset (tilaaja_id, toimitettu, hinta, lentoID, added) VALUES (1, true, 25, 2, now());
INSERT INTO tilaus(tilaaja_id, toimitettu, yhteishinta, lentoid, added) VALUES (2, false, 500, 2, now());

INSERT INTO tilaustiedot( tilausid, tavaraid, kpl) VALUES ( 1, 25, 5);

