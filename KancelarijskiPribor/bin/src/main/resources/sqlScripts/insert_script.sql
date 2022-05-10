INSERT INTO kategorija (kategorija_id,naziv_kategorija)
	VALUES(1, 'Skolski pribor');
INSERT INTO kategorija (kategorija_id,naziv_kategorija)
	VALUES(2,'Kancelarijski pribor');
INSERT INTO kategorija (kategorija_id,naziv_kategorija)
	VALUES(3,'Pribor za pisanje');
	
INSERT INTO proizvodjac (proizvodjac_id,naziv_proizvodjaca)
	VALUES(1,'Pelikan');
INSERT INTO proizvodjac (proizvodjac_id,naziv_proizvodjaca)
	VALUES(2,'Staedtler');
INSERT INTO proizvodjac (proizvodjac_id,naziv_proizvodjaca)
	VALUES(3,'Play');
	
INSERT INTO vrsta (vrsta_id,naziv_vrste)
	VALUES(1,'A5 linije');
INSERT INTO vrsta (vrsta_id,naziv_vrste)
	VALUES(2,'A4 kvadratici');
INSERT INTO vrsta (vrsta_id,naziv_vrste)
	VALUES(3,'HB');
INSERT INTO vrsta (vrsta_id,naziv_vrste)
	VALUES(4,'A4 sa gumom');
	
INSERT INTO proizvod (proizvod_id,naziv,kategorija_id,vrsta_id,proizvodjac_id,jedinica,cena,na_stanju)
	VALUES(1,'sveska',1,1,3,'komad',75,25);
INSERT INTO proizvod (proizvod_id,naziv,kategorija_id,vrsta_id,proizvodjac_id,jedinica,cena,na_stanju)
	VALUES(2,'sveska',1,2,3,'komad',170,30);
INSERT INTO proizvod (proizvod_id,naziv,kategorija_id,vrsta_id,proizvodjac_id,jedinica,cena,na_stanju)
	VALUES(3,'fascikla',2,4,3,'komad',205,15);
INSERT INTO proizvod (proizvod_id,naziv,kategorija_id,vrsta_id,proizvodjac_id,jedinica,cena,na_stanju)
	VALUES(4,'olovka',3,3,2,'komad',45,33);
INSERT INTO proizvod (proizvod_id,naziv,kategorija_id,vrsta_id,proizvodjac_id,jedinica,cena,na_stanju)
	VALUES(5,'olovka',3,3,1,'komad',40,35);
	
INSERT INTO korisnik (korisnik_id,tip_korisnika,jmbg,ime,prezime,kontakt,email,korisnicko_ime,lozinka)
	VALUES(1,'zaposleni','1234567891234','Pera','Peric','060123123','pera.peric@mail.com','pera','pera123');
INSERT INTO korisnik (korisnik_id,tip_korisnika,jmbg,ime,prezime,kontakt,email,korisnicko_ime,lozinka)
	VALUES(2,'zaposleni','9876543210987','Mara','Maric','060321321','mara.maric@mail.com','mara','mara123');
	
INSERT INTO korisnik (korisnik_id,tip_korisnika,jmbg,ime,prezime,kontakt,email,korisnicko_ime,lozinka,stanje_na_racunu)
	VALUES(3,'kupac','1231236546547','Nikola','Nikolic','060147147','nikola.nikolic@mail.com','nikola','nikola123',1250);
INSERT INTO korisnik (korisnik_id,tip_korisnika,jmbg,ime,prezime,kontakt,email,korisnicko_ime,lozinka,stanje_na_racunu)
	VALUES(4,'kupac','1472583698521','Petra','Petric','060258258','petra.petric@mail.com','petra','petra123',750);

INSERT INTO porudzbina (porudzbina_id,adresa_isporuke,korisnik_id)
	VALUES(1,'Devet Jugovica 35',3);
INSERT INTO porudzbina (porudzbina_id,adresa_isporuke,korisnik_id)
	VALUES(2,'Crice Milice 12',3);
INSERT INTO porudzbina (porudzbina_id,adresa_isporuke,korisnik_id)
	VALUES(3,'Radnicka 14',4);
	
INSERT INTO poruceni_proizvod(porudzbina_id,proizvod_id,kolicina)
	VALUES(1,1,2);
INSERT INTO poruceni_proizvod(porudzbina_id,proizvod_id,kolicina)
	VALUES(1,2,3);
INSERT INTO poruceni_proizvod(porudzbina_id,proizvod_id,kolicina)
	VALUES(1,3,1);
INSERT INTO poruceni_proizvod(porudzbina_id,proizvod_id,kolicina)
	VALUES(2,4,2);
INSERT INTO poruceni_proizvod(porudzbina_id,proizvod_id,kolicina)
	VALUES(3,5,2);
	
SELECT * from vrsta;
SELECT * from kategorija;
SELECT * from proizvodjac;
SELECT * from korisnik;
SELECT * from proizvod;
SELECT * from porudzbina;
	
	
	
	
	
	
	
	
	
	
	
	

