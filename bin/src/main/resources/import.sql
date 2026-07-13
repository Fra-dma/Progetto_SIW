-- ==========================================
-- 1. UTENTI
-- ==========================================
INSERT INTO utente (id, username, password, ruolo) VALUES (nextval('utente_seq'), 'admin', 'admin123', 'ADMIN');
INSERT INTO utente (id, username, password, ruolo) VALUES (nextval('utente_seq'), 'mario.rossi', 'user123', 'USER');
INSERT INTO utente (id, username, password, ruolo) VALUES (nextval('utente_seq'), 'luigi.bianchi', 'user123', 'USER');

-- ==========================================
-- 2. TORNEI
-- ==========================================
INSERT INTO torneo (id, nome, anno, descrizione) VALUES (nextval('torneo_seq'), 'Campionato Estivo', 2026, 'Il torneo amatoriale piu prestigioso della capitale.');
INSERT INTO torneo (id, nome, anno, descrizione) VALUES (nextval('torneo_seq'), 'Coppa Invernale', 2026, 'Torneo a eliminazione diretta per scaldare i motori.');

-- ==========================================
-- 3. SQUADRE
-- ==========================================
INSERT INTO squadra (id, nome, anno_di_fondazione, citta) VALUES (nextval('squadra_seq'), 'Lupi Roma', 2015, 'Roma');
INSERT INTO squadra (id, nome, anno_di_fondazione, citta) VALUES (nextval('squadra_seq'), 'Leoni Milano', 2018, 'Milano');
INSERT INTO squadra (id, nome, anno_di_fondazione, citta) VALUES (nextval('squadra_seq'), 'Aquile Napoli', 2020, 'Napoli');
INSERT INTO squadra (id, nome, anno_di_fondazione, citta) VALUES (nextval('squadra_seq'), 'Tigri Torino', 2019, 'Torino');

-- ==========================================
-- 4. GIOCATORI
-- ==========================================
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Francesco', 'Totti', '1976-09-27', 'Attaccante', 180, (SELECT id FROM squadra WHERE nome = 'Lupi Roma'));
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Daniele', 'De Rossi', '1983-07-24', 'Centrocampista', 184, (SELECT id FROM squadra WHERE nome = 'Lupi Roma'));
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Alessandro', 'Florenzi', '1991-03-11', 'Difensore', 173, (SELECT id FROM squadra WHERE nome = 'Lupi Roma'));
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Paolo', 'Maldini', '1968-06-26', 'Difensore', 186, (SELECT id FROM squadra WHERE nome = 'Leoni Milano'));
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Andrea', 'Pirlo', '1979-05-19', 'Centrocampista', 177, (SELECT id FROM squadra WHERE nome = 'Leoni Milano'));
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Filippo', 'Inzaghi', '1973-08-09', 'Attaccante', 181, (SELECT id FROM squadra WHERE nome = 'Leoni Milano'));
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Diego', 'Maradona', '1960-10-30', 'Attaccante', 165, (SELECT id FROM squadra WHERE nome = 'Aquile Napoli'));
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Marek', 'Hamsik', '1987-07-27', 'Centrocampista', 183, (SELECT id FROM squadra WHERE nome = 'Aquile Napoli'));
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Kalidou', 'Koulibaly', '1991-06-20', 'Difensore', 187, (SELECT id FROM squadra WHERE nome = 'Aquile Napoli'));
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Alessandro', 'Del Piero', '1974-11-09', 'Attaccante', 174, (SELECT id FROM squadra WHERE nome = 'Tigri Torino'));
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Gianluigi', 'Buffon', '1978-01-28', 'Portiere', 192, (SELECT id FROM squadra WHERE nome = 'Tigri Torino'));
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Giorgio', 'Chiellini', '1984-08-14', 'Difensore', 187, (SELECT id FROM squadra WHERE nome = 'Tigri Torino'));

-- ==========================================
-- 5. ARBITRI
-- ==========================================
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (nextval('arbitro_seq'), 'Pierluigi', 'Collina', 'ARB-001');
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (nextval('arbitro_seq'), 'Nicola', 'Rizzoli', 'ARB-002');

-- ==========================================
-- 6. ISCRIZIONE SQUADRE AI TORNEI
-- ==========================================
INSERT INTO torneo_squadre_partecipanti (tornei_id, squadre_partecipanti_id) VALUES ((SELECT id FROM torneo WHERE nome = 'Campionato Estivo'), (SELECT id FROM squadra WHERE nome = 'Lupi Roma'));
INSERT INTO torneo_squadre_partecipanti (tornei_id, squadre_partecipanti_id) VALUES ((SELECT id FROM torneo WHERE nome = 'Campionato Estivo'), (SELECT id FROM squadra WHERE nome = 'Leoni Milano'));
INSERT INTO torneo_squadre_partecipanti (tornei_id, squadre_partecipanti_id) VALUES ((SELECT id FROM torneo WHERE nome = 'Campionato Estivo'), (SELECT id FROM squadra WHERE nome = 'Aquile Napoli'));
INSERT INTO torneo_squadre_partecipanti (tornei_id, squadre_partecipanti_id) VALUES ((SELECT id FROM torneo WHERE nome = 'Campionato Estivo'), (SELECT id FROM squadra WHERE nome = 'Tigri Torino'));
INSERT INTO torneo_squadre_partecipanti (tornei_id, squadre_partecipanti_id) VALUES ((SELECT id FROM torneo WHERE nome = 'Coppa Invernale'), (SELECT id FROM squadra WHERE nome = 'Lupi Roma'));
INSERT INTO torneo_squadre_partecipanti (tornei_id, squadre_partecipanti_id) VALUES ((SELECT id FROM torneo WHERE nome = 'Coppa Invernale'), (SELECT id FROM squadra WHERE nome = 'Tigri Torino'));

-- ==========================================
-- 7. PARTITE 
-- ==========================================
INSERT INTO partita (id, dataeora, luogo, goals_home, goals_away, stato, arbitro_id, squadra_casa_id, squadra_ospite_id, torneo_id) VALUES (nextval('partita_seq'), '2026-06-01 18:00:00', 'Stadio Olimpico', 2, 1, 'PLAYED', (SELECT id FROM arbitro WHERE codice_arbitrale = 'ARB-001'), (SELECT id FROM squadra WHERE nome = 'Lupi Roma'), (SELECT id FROM squadra WHERE nome = 'Leoni Milano'), (SELECT id FROM torneo WHERE nome = 'Campionato Estivo'));
INSERT INTO partita (id, dataeora, luogo, goals_home, goals_away, stato, arbitro_id, squadra_casa_id, squadra_ospite_id, torneo_id) VALUES (nextval('partita_seq'), '2026-06-05 20:45:00', 'Stadio San Paolo', 1, 1, 'PLAYED', (SELECT id FROM arbitro WHERE codice_arbitrale = 'ARB-002'), (SELECT id FROM squadra WHERE nome = 'Aquile Napoli'), (SELECT id FROM squadra WHERE nome = 'Tigri Torino'), (SELECT id FROM torneo WHERE nome = 'Campionato Estivo'));
INSERT INTO partita (id, dataeora, luogo, goals_home, goals_away, stato, arbitro_id, squadra_casa_id, squadra_ospite_id, torneo_id) VALUES (nextval('partita_seq'), '2026-07-15 18:30:00', 'Stadio San Siro', 0, 0, 'SCHEDULED', (SELECT id FROM arbitro WHERE codice_arbitrale = 'ARB-001'), (SELECT id FROM squadra WHERE nome = 'Leoni Milano'), (SELECT id FROM squadra WHERE nome = 'Aquile Napoli'), (SELECT id FROM torneo WHERE nome = 'Campionato Estivo'));
INSERT INTO partita (id, dataeora, luogo, goals_home, goals_away, stato, arbitro_id, squadra_casa_id, squadra_ospite_id, torneo_id) VALUES (nextval('partita_seq'), '2026-12-10 14:00:00', 'Stadio Olimpico', 0, 0, 'SCHEDULED', (SELECT id FROM arbitro WHERE codice_arbitrale = 'ARB-002'), (SELECT id FROM squadra WHERE nome = 'Lupi Roma'), (SELECT id FROM squadra WHERE nome = 'Tigri Torino'), (SELECT id FROM torneo WHERE nome = 'Coppa Invernale'));