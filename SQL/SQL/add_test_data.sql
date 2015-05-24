INSERT INTO TavaranValmistaja (nimi, maa, added) VALUES ('Nokia', 'Valmistaa matkapuhelimia', now());
INSERT INTO TavaranValmistaja (nimi, maa, added) VALUES ('Rolex', 'Valmistaa Kelloja', now());
INSERT INTO Tavara (valmistaja_id, nimi, hinta, saatavilla, varastossa, kuvaus, julkaistu, added) VALUES (1, 'Nokia 3330', 250, true, 15, 'Great phone', now(), now());
INSERT INTO Tavara (valmistaja_id, nimi, hinta, saatavilla, varastossa, kuvaus, julkaistu, added) VALUES (2, 'Rolex 331250', 25055, true, 1, 'Great clock', now(), now());
INSERT INTO AterianValmistaja (nimi, maa, added) VALUES ('Unicafe', 'Valmistaa ruokaa', now());
INSERT INTO Ateria (Aterianvalmistaja_id, nimi, hinta, saatavilla, kuvaus, julkaistu, added) VALUES (1, 'Uunimakkara', 2, true,  'Herkullinen ja tuore uunimakkara-ateria', now(), now());
INSERT INTO Kayttaja (username, password, admin, maa, added) VALUES ('adminx', 'adminx', true, 'finland', now());
INSERT INTO Tilaukset (tilaaja_id, toimitettu, hinta, lentoID, added) VALUES (1, true, 25, 12, now());