/* Dodavanje korisnika */
insert into KORISNIK values(1,'Novi Sad',null,'salerac@gmail.com',true,'Aleksandar','123','1234','REGISTERED_USER','Rac');
insert into KORISNIK values(2,'Novi Sad',null,'milos23st@gmail.com',true,'Milos','ftn','1234','FanZone_ADMIN','Stefanovic');
insert into KORISNIK values(3,'Novi Sad',null,'admin@gmail.com',true,'Admin','admin','1234','ADMIN','System');

/* Dodavanje bioskopa */
insert into REPERTOIRE (id, cinema_id) values (1, null);
insert into CINEMA values(1,'Big Shopping Center','Novi Sad','Neki opis',true,'Cinestar',1); 
update REPERTOIRE set cinema_id=1 where id=1;

insert into REPERTOIRE (id, cinema_id) values (2, null);
insert into CINEMA values(2,'NS Centar','Novi Sad','Neki opis',true,'Arena Cineplex',2); 
update REPERTOIRE set cinema_id=2 where id=2;

insert into REPERTOIRE (id, cinema_id) values (3, null);
insert into CINEMA values(3,'Pozorišni trg 1','Novi Sad','Neki opis',false,'Srpsko Narodno Pozoriste',3); 
update REPERTOIRE set cinema_id=3 where id=3;

insert into REPERTOIRE (id, cinema_id) values (4, null);
insert into CINEMA values(4,'Ignjata Pavlasa Игњата Павласа 4','Novi Sad','Neki opis',false,'Pozoriste Mladih',4); 
update REPERTOIRE set cinema_id=4 where id=4;

/* Sedista */
insert into SEAT values (1,2,'REGULAR');
insert into SEAT values (2,41,'VIP');
insert into SEAT values (3,13,'VIP');
insert into SEAT values (4,11,'REGULAR');
insert into SEAT values (5,221,'VIP');
insert into SEAT values (6,13,'REGULAR');
insert into SEAT values (7,151,'REGULAR');

/* Filmovi */
insert into MOVIE values(1,'Opis','Guillermo Del Toro','120',null,true,'Pacific Rim',200.00,4.5,1);
insert into MOVIE values(2,'Opis','James Cameron','180',null,true,'Avatar',300.00,3,2);

insert into REPERTOIRE_MOVIES values(1,1);
insert into REPERTOIRE_MOVIES values(1,2);

/* Sale */
insert into SHOW_ROOM (id, number, length, width) values(1,1,20,10);
insert into SHOW_ROOM (id, number, length, width) values(2,2,20,10);
insert into SHOW_ROOM (id, number, length, width) values(3,3,20,10);
insert into SHOW_ROOM (id, number, length, width) values(4,4,20,10);


/* Projekcije */
insert into PROJECTION values(1,parsedatetime('2018-04-22 20:00','yyyy-MM-dd HH:mm'), 300,1);
insert into PROJECTION values(2,parsedatetime('2018-04-22 22:00','yyyy-MM-dd HH:mm'), 300,2);
insert into PROJECTION values(3,parsedatetime('2018-05-17 18:00','yyyy-MM-dd HH:mm'), 300,3);
insert into PROJECTION values(4,parsedatetime('2018-05-17 18:00','yyyy-MM-dd HH:mm'), 300,4);
insert into PROJECTION values(5,parsedatetime('2018-04-22 22:00','yyyy-MM-dd HH:mm'), 300,3);


insert into MOVIE_PROJECTIONS values(1,1);
insert into MOVIE_PROJECTIONS values(1,5);
insert into MOVIE_PROJECTIONS values(1,3);
insert into MOVIE_PROJECTIONS values(1,4);
insert into MOVIE_PROJECTIONS values(2,2);

/* Rezervacije */
insert into RESERVATION values(1,true,1,1,1,4,1);
insert into RESERVATION values(2,true,4,2,2,6,1);
insert into RESERVATION values(3,true,4,2,3,6,1);
insert into RESERVATION values(4,true,1,1,1,7,1);
insert into RESERVATION values(5,true,1,1,1,5,1);
insert into RESERVATION values(6,true,1,1,1,2,1);
insert into RESERVATION values(7,true,1,1,1,1,1);

insert into PROJECTION_RESERVATIONS values(1,1);
insert into PROJECTION_RESERVATIONS values(2,2);
insert into PROJECTION_RESERVATIONS values(3,3);
insert into PROJECTION_RESERVATIONS values(1,4);
insert into PROJECTION_RESERVATIONS values(1,5);
insert into PROJECTION_RESERVATIONS values(1,6);
insert into PROJECTION_RESERVATIONS values(1,7);

/*Requisites*/
insert into REQUISITE values(1,'Guitar from movie','no picture','Guitar','10000','true');
insert into REQUISITE values(2,'Helmet from movie-replica','no picture','Helmet','6000','false');
insert into REQUISITE values(3,'Suicide Squad Harley Quinn Jacket','no picture','Jacket','5000','true');
insert into REQUISITE values(4,'Adult Rey Costume - Star Wars 7','no picture','Costume','20000','false');

/*UserAds*/

insert into USER_AD values(1,'false','1',parsedatetime('2018-05-17 18:00','yyyy-MM-dd HH:mm'),'description 1','no picture','userAd1');
insert into USER_AD values(2,'false','1',parsedatetime('2018-05-22 20:00','yyyy-MM-dd HH:mm'),'description 2','no picture','userAd2');


