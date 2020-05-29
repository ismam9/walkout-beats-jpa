/* Prodcutor inserts */
insert into productor(nombre, apellido, descripcion, especificacion, ciudad, email, nickname, cantante, productorm, sonido, foto) values('Carlos', 'Saura', 'Worst producer', 'Masterizacion', 'Granada', 'carloscg@gmail.com', 'kowl', TRUE, '', '', '');
insert into productor(nombre, apellido, descripcion, especificacion, ciudad, email, nickname, cantante, productorm, sonido, foto) values('Ismael', 'Torres', 'Best producer', 'Grabacion', 'Madrid', 'ismaeltp@gmail.com', 'buenagente', '', '', '', '');
insert into productor(nombre, apellido, descripcion, especificacion, ciudad, email, nickname, cantante, productorm, sonido, foto) values('Julian', 'Saura', 'Random producer', 'Masterizacion', 'Cordoba', 'Julian@gmail.com', 'tristan', '', '', '', '');
insert into productor(nombre, apellido, descripcion, especificacion, ciudad, email, nickname, cantante, productorm, sonido, foto) values('Alvaro', 'Torres', 'Gnarly producer', 'Grabacion', 'Madrid', 'Alvaro@gmail.com', 'akaeltriste', '', '', '', '');
insert into productor(nombre, apellido, descripcion, especificacion, ciudad, email, nickname, cantante, productorm, sonido, foto) values('Laureano', 'Folgar', 'Ugly producer', 'Sonidi', 'Albacete', 'Laureanog@gmail.com', 'aka', '', '', '', '');
insert into productor(nombre, apellido, descripcion, especificacion, ciudad, email, nickname, cantante, productorm, sonido, foto) values('Manuela', 'Hernandez', 'Handsome producer', 'Cantante akapella', 'Marbella', 'Manuela@gmail.com', 'wuep', '', '', '', '');


/* Categorias */

insert into db_springboot_walkout.categoria(nombre, create_at) values("Techno", NOW());
insert into db_springboot_walkout.categoria(nombre, create_at) values("House", NOW());
insert into db_springboot_walkout.categoria(nombre, create_at) values("Hip-Hop", NOW());
insert into db_springboot_walkout.categoria(nombre, create_at) values("RAP", NOW());
insert into db_springboot_walkout.categoria(nombre, create_at) values("Lo-Fi", NOW());
insert into db_springboot_walkout.categoria(nombre, create_at) values("Mal", NOW());
insert into db_springboot_walkout.categoria(nombre, create_at) values("Techno", NOW());
insert into db_springboot_walkout.categoria(nombre, create_at) values("RAP", NOW());
insert into db_springboot_walkout.categoria(nombre, create_at) values("POP", NOW());
insert into db_springboot_walkout.categoria(nombre, create_at) values("Lo-Fi", NOW());
insert into db_springboot_walkout.categoria(nombre, create_at) values("Mal", NOW());


/*Beats inserts*/

insert into audio(nombre, duracion, precio, tipo, create_at, foto, audio, active, productor_id, categoria_id) values('lechugaremimix', 30, 120, 'Leasing', NOW(), '', '', TRUE, 4, 3);
insert into audio(nombre, duracion, precio, tipo, create_at, foto, audio, active, productor_id, categoria_id) values('wuepremix', 30, 120, 'Leasing', NOW(), '', '', TRUE, 3, 3);
insert into audio(nombre, duracion, precio, tipo, create_at, foto, audio, active, productor_id, categoria_id) values('lacacerola', 30, 0, 'Free', NOW(), '', '', TRUE, 2, 2);
insert into audio(nombre, duracion, precio, tipo, create_at, foto, audio, active, productor_id, categoria_id) values('careloadamix', 30, 0, 'Free', NOW(), '', '', TRUE, 2, 1);
insert into audio(nombre, duracion, precio, tipo, create_at, foto, audio, active, productor_id, categoria_id) values('tusmuertosmix', 30, 0, 'Free', NOW(), '', '', FALSE, 6, 5);
insert into audio(nombre, duracion, precio, tipo, create_at, foto, audio, active, productor_id, categoria_id) values('qmalmix', 30, 0, 'Free', NOW(), '', '', FALSE, 6, 4);
insert into audio(nombre, duracion, precio, tipo, create_at, foto, audio, active, productor_id, categoria_id) values('mxpormadrid-vicecity', 30,  0, 'Free', NOW(), '', '', FALSE, 5, 6);
insert into audio(nombre, duracion, precio, tipo, create_at, foto, audio, active, productor_id, categoria_id) values('qmalcolega-nomehables', 30,  0, 'Free', NOW(), '', '', FALSE, 5, 5);


/* Album inserts */

insert into album(descripcion, foto, create_at, productor_id) values('el peor album de la historia', '', NOW(), 5);
insert into item_album(album_id, audio_id) values(1, 7);
insert into item_album(album_id, audio_id) values(1, 8);

insert into album(descripcion, foto, create_at, productor_id) values('el peor album de la historia', '', NOW(), 6);
insert into item_album(album_id, audio_id) values(2, 5);
insert into item_album(album_id, audio_id) values(2, 6);

INSERT INTO users (username, password, enabled) VALUES ('andres', '$2a$10$ZnSD00F9jLHL1e14fn768OR/M3xQVEUDCdXkk3xyD/ImFhw/u6fna', 1);
INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$4Xi3Abu6z/gPsazOleua8e3tgAGWsYZ6/QRHpa7khw3AlsStFHY3m', 1);
INSERT INTO users (username, password, enabled) VALUES ('buenagente', '$2a$10$ylTdD2j/doPsFkHjl.zmZu8jjBfYaosTSRxIDineQEge6JFHnAArG', 1);

insert into authorities (user_id, authority) values(1, 'ROLE_USER');

insert into authorities (user_id, authority) values(2, 'ROLE_ADMIN');
insert into authorities (user_id, authority) values(2, 'ROLE_USER');

insert into authorities (user_id, authority) values(3, 'ROLE_PRODUCTOR');
insert into authorities (user_id, authority) values(3, 'ROLE_USER');
