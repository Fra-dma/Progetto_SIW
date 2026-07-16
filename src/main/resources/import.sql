-- ==========================================
-- 1. TORNEI
-- ==========================================
INSERT INTO torneo (id, nome, anno, descrizione) VALUES (nextval('torneo_seq'), 'Campionato Estivo', 2026, 'Il torneo amatoriale piu prestigioso della capitale.');
INSERT INTO torneo (id, nome, anno, descrizione) VALUES (nextval('torneo_seq'), 'Coppa Invernale', 2026, 'Torneo a eliminazione diretta per scaldare i motori.');
-- [DATI DI TEST ESPERIMENTO]
INSERT INTO torneo (id, nome, anno, descrizione) VALUES (nextval('torneo_seq'), 'Test analisi sperimentale', 2026, 'Torneo finto per testare le prestazioni Hibernate LAZY vs EAGER');

-- ==========================================
-- 2. SQUADRE
-- ==========================================
INSERT INTO squadra (id, nome, anno_di_fondazione, citta) VALUES (nextval('squadra_seq'), 'Lupi Roma', 2015, 'Roma');
INSERT INTO squadra (id, nome, anno_di_fondazione, citta) VALUES (nextval('squadra_seq'), 'Leoni Milano', 2018, 'Milano');
INSERT INTO squadra (id, nome, anno_di_fondazione, citta) VALUES (nextval('squadra_seq'), 'Aquile Napoli', 2020, 'Napoli');
INSERT INTO squadra (id, nome, anno_di_fondazione, citta) VALUES (nextval('squadra_seq'), 'Tigri Torino', 2019, 'Torino');
-- [DATI DI TEST ESPERIMENTO]
INSERT INTO squadra (id, nome, anno_di_fondazione, citta) VALUES (nextval('squadra_seq'), 'Squadra Alfa', 2026, 'Citta Test 1');
INSERT INTO squadra (id, nome, anno_di_fondazione, citta) VALUES (nextval('squadra_seq'), 'Squadra Beta', 2026, 'Citta Test 2');
INSERT INTO squadra (id, nome, anno_di_fondazione, citta) VALUES (nextval('squadra_seq'), 'Squadra Gamma', 2026, 'Citta Test 3');

-- ==========================================
-- 3. GIOCATORI
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
-- [DATI DI TEST ESPERIMENTO]
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Mario', 'Test', '2000-01-01', 'Attaccante', 180, (SELECT id FROM squadra WHERE nome = 'Squadra Alfa'));
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Luigi', 'Test', '2000-02-02', 'Difensore', 185, (SELECT id FROM squadra WHERE nome = 'Squadra Beta'));
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Toad', 'Test', '2000-03-03', 'Portiere', 170, (SELECT id FROM squadra WHERE nome = 'Squadra Gamma'));

-- ==========================================
-- 4. ARBITRI
-- ==========================================
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (nextval('arbitro_seq'), 'Pierluigi', 'Collina', 'ARB-001');
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (nextval('arbitro_seq'), 'Nicola', 'Rizzoli', 'ARB-002');

-- ==========================================
-- 5. ISCRIZIONE SQUADRE AI TORNEI
-- ==========================================
INSERT INTO torneo_squadre_partecipanti (tornei_id, squadre_partecipanti_id) VALUES ((SELECT id FROM torneo WHERE nome = 'Campionato Estivo'), (SELECT id FROM squadra WHERE nome = 'Lupi Roma'));
INSERT INTO torneo_squadre_partecipanti (tornei_id, squadre_partecipanti_id) VALUES ((SELECT id FROM torneo WHERE nome = 'Campionato Estivo'), (SELECT id FROM squadra WHERE nome = 'Leoni Milano'));
INSERT INTO torneo_squadre_partecipanti (tornei_id, squadre_partecipanti_id) VALUES ((SELECT id FROM torneo WHERE nome = 'Campionato Estivo'), (SELECT id FROM squadra WHERE nome = 'Aquile Napoli'));
INSERT INTO torneo_squadre_partecipanti (tornei_id, squadre_partecipanti_id) VALUES ((SELECT id FROM torneo WHERE nome = 'Campionato Estivo'), (SELECT id FROM squadra WHERE nome = 'Tigri Torino'));
INSERT INTO torneo_squadre_partecipanti (tornei_id, squadre_partecipanti_id) VALUES ((SELECT id FROM torneo WHERE nome = 'Coppa Invernale'), (SELECT id FROM squadra WHERE nome = 'Lupi Roma'));
INSERT INTO torneo_squadre_partecipanti (tornei_id, squadre_partecipanti_id) VALUES ((SELECT id FROM torneo WHERE nome = 'Coppa Invernale'), (SELECT id FROM squadra WHERE nome = 'Tigri Torino'));
-- [DATI DI TEST ESPERIMENTO]
INSERT INTO torneo_squadre_partecipanti (tornei_id, squadre_partecipanti_id) VALUES ((SELECT id FROM torneo WHERE nome = 'Test analisi sperimentale'), (SELECT id FROM squadra WHERE nome = 'Squadra Alfa'));
INSERT INTO torneo_squadre_partecipanti (tornei_id, squadre_partecipanti_id) VALUES ((SELECT id FROM torneo WHERE nome = 'Test analisi sperimentale'), (SELECT id FROM squadra WHERE nome = 'Squadra Beta'));
INSERT INTO torneo_squadre_partecipanti (tornei_id, squadre_partecipanti_id) VALUES ((SELECT id FROM torneo WHERE nome = 'Test analisi sperimentale'), (SELECT id FROM squadra WHERE nome = 'Squadra Gamma'));

-- ==========================================
-- 6. PARTITE 
-- ==========================================
INSERT INTO partita (id, dataeora, luogo, goals_home, goals_away, stato, arbitro_id, squadra_casa_id, squadra_ospite_id, torneo_id) VALUES (nextval('partita_seq'), '2026-06-01 18:00:00', 'Stadio Olimpico', 2, 1, 'PLAYED', (SELECT id FROM arbitro WHERE codice_arbitrale = 'ARB-001'), (SELECT id FROM squadra WHERE nome = 'Lupi Roma'), (SELECT id FROM squadra WHERE nome = 'Leoni Milano'), (SELECT id FROM torneo WHERE nome = 'Campionato Estivo'));
INSERT INTO partita (id, dataeora, luogo, goals_home, goals_away, stato, arbitro_id, squadra_casa_id, squadra_ospite_id, torneo_id) VALUES (nextval('partita_seq'), '2026-06-05 20:45:00', 'Stadio San Paolo', 1, 1, 'PLAYED', (SELECT id FROM arbitro WHERE codice_arbitrale = 'ARB-002'), (SELECT id FROM squadra WHERE nome = 'Aquile Napoli'), (SELECT id FROM squadra WHERE nome = 'Tigri Torino'), (SELECT id FROM torneo WHERE nome = 'Campionato Estivo'));
INSERT INTO partita (id, dataeora, luogo, goals_home, goals_away, stato, arbitro_id, squadra_casa_id, squadra_ospite_id, torneo_id) VALUES (nextval('partita_seq'), '2026-07-15 18:30:00', 'Stadio San Siro', 0, 0, 'SCHEDULED', (SELECT id FROM arbitro WHERE codice_arbitrale = 'ARB-001'), (SELECT id FROM squadra WHERE nome = 'Leoni Milano'), (SELECT id FROM squadra WHERE nome = 'Aquile Napoli'), (SELECT id FROM torneo WHERE nome = 'Campionato Estivo'));
INSERT INTO partita (id, dataeora, luogo, goals_home, goals_away, stato, arbitro_id, squadra_casa_id, squadra_ospite_id, torneo_id) VALUES (nextval('partita_seq'), '2026-12-10 14:00:00', 'Stadio Olimpico', 0, 0, 'SCHEDULED', (SELECT id FROM arbitro WHERE codice_arbitrale = 'ARB-002'), (SELECT id FROM squadra WHERE nome = 'Lupi Roma'), (SELECT id FROM squadra WHERE nome = 'Tigri Torino'), (SELECT id FROM torneo WHERE nome = 'Coppa Invernale'));
-- [DATI DI TEST ESPERIMENTO] (Ho inserito risultati a caso e stato PLAYED così li puoi vedere anche nella classifica)
INSERT INTO partita (id, dataeora, luogo, goals_home, goals_away, stato, arbitro_id, squadra_casa_id, squadra_ospite_id, torneo_id) VALUES (nextval('partita_seq'), '2026-08-01 10:00:00', 'Stadio Test 1', 1, 0, 'PLAYED', (SELECT id FROM arbitro WHERE codice_arbitrale = 'ARB-001'), (SELECT id FROM squadra WHERE nome = 'Squadra Alfa'), (SELECT id FROM squadra WHERE nome = 'Squadra Beta'), (SELECT id FROM torneo WHERE nome = 'Test analisi sperimentale'));
INSERT INTO partita (id, dataeora, luogo, goals_home, goals_away, stato, arbitro_id, squadra_casa_id, squadra_ospite_id, torneo_id) VALUES (nextval('partita_seq'), '2026-08-02 10:00:00', 'Stadio Test 2', 2, 2, 'PLAYED', (SELECT id FROM arbitro WHERE codice_arbitrale = 'ARB-002'), (SELECT id FROM squadra WHERE nome = 'Squadra Beta'), (SELECT id FROM squadra WHERE nome = 'Squadra Gamma'), (SELECT id FROM torneo WHERE nome = 'Test analisi sperimentale'));
INSERT INTO partita (id, dataeora, luogo, goals_home, goals_away, stato, arbitro_id, squadra_casa_id, squadra_ospite_id, torneo_id) VALUES (nextval('partita_seq'), '2026-08-03 10:00:00', 'Stadio Test 3', 0, 3, 'PLAYED', (SELECT id FROM arbitro WHERE codice_arbitrale = 'ARB-001'), (SELECT id FROM squadra WHERE nome = 'Squadra Gamma'), (SELECT id FROM squadra WHERE nome = 'Squadra Alfa'), (SELECT id FROM torneo WHERE nome = 'Test analisi sperimentale'));
INSERT INTO partita (id, dataeora, luogo, goals_home, goals_away, stato, arbitro_id, squadra_casa_id, squadra_ospite_id, torneo_id) VALUES (nextval('partita_seq'), '2026-08-04 10:00:00', 'Stadio Test 4', 1, 1, 'PLAYED', (SELECT id FROM arbitro WHERE codice_arbitrale = 'ARB-002'), (SELECT id FROM squadra WHERE nome = 'Squadra Alfa'), (SELECT id FROM squadra WHERE nome = 'Squadra Gamma'), (SELECT id FROM torneo WHERE nome = 'Test analisi sperimentale'));
INSERT INTO partita (id, dataeora, luogo, goals_home, goals_away, stato, arbitro_id, squadra_casa_id, squadra_ospite_id, torneo_id) VALUES (nextval('partita_seq'), '2026-08-05 10:00:00', 'Stadio Test 5', 0, 0, 'SCHEDULED', (SELECT id FROM arbitro WHERE codice_arbitrale = 'ARB-001'), (SELECT id FROM squadra WHERE nome = 'Squadra Beta'), (SELECT id FROM squadra WHERE nome = 'Squadra Alfa'), (SELECT id FROM torneo WHERE nome = 'Test analisi sperimentale'));
INSERT INTO partita (id, dataeora, luogo, goals_home, goals_away, stato, arbitro_id, squadra_casa_id, squadra_ospite_id, torneo_id) VALUES (nextval('partita_seq'), '2026-08-06 10:00:00', 'Stadio Test 6', 0, 0, 'SCHEDULED', (SELECT id FROM arbitro WHERE codice_arbitrale = 'ARB-002'), (SELECT id FROM squadra WHERE nome = 'Squadra Gamma'), (SELECT id FROM squadra WHERE nome = 'Squadra Beta'), (SELECT id FROM torneo WHERE nome = 'Test analisi sperimentale'));