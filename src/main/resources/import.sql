/* Prodcutor inserts */
insert into productor(nombre, apellido, ciudad, email, nickname, foto) values('Carlos', 'Saura', 'Granada', 'carloscg@gmail.com', 'kowl', '');
insert into productor(nombre, apellido, ciudad, email, nickname, foto) values('Ismael', 'Torres', 'Madrid', 'ismaeltp@gmail.com', 'buenagente', '');
insert into productor(nombre, apellido, ciudad, email, nickname, foto) values('Julian', 'Saura', 'Cordoba', 'Julian@gmail.com', 'tristan', '');
insert into productor(nombre, apellido, ciudad, email, nickname, foto) values('Alvaro', 'Torres', 'Madrid', 'Alvaro@gmail.com', 'akaeltriste', '');
insert into productor(nombre, apellido, ciudad, email, nickname, foto) values('Laureano', 'Folgar', 'Albacete', 'Laureanog@gmail.com', 'aka', '');
insert into productor(nombre, apellido, ciudad, email, nickname, foto) values('Manuela', 'Hernandez', 'Marbella', 'Manuela@gmail.com', 'wuep', '');


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

insert into beat(nombre, precio, duracion, create_at, foto, productor_id, categoria_id) values('lechugaremimix', 120, 30, NOW(), '', 4, 3);
insert into beat(nombre, precio, duracion, create_at, foto, productor_id, categoria_id) values('wuepremix', 120, 30, NOW(), '', 3, 3);
insert into beat(nombre, precio, duracion, create_at, foto, productor_id, categoria_id) values('lacacerola', 120, 30, NOW(), '', 2, 2);
insert into beat(nombre, precio, duracion, create_at, foto, productor_id, categoria_id) values('careloadamix', 120, 30, NOW(), '', 2, 1);
insert into beat(nombre, precio, duracion, create_at, foto, productor_id, categoria_id) values('tusmuertosmix', 120, 30, NOW(), '', 6, 5);
insert into beat(nombre, precio, duracion, create_at, foto, productor_id, categoria_id) values('qmalmix', 120, 30, NOW(), '', 6, 4);
insert into beat(nombre, precio, duracion, create_at, foto, productor_id, categoria_id) values('mxpormadrid-vicecity', 120, 30, NOW(), '', 5, 6);
insert into beat(nombre, precio, duracion, create_at, foto, productor_id, categoria_id) values('qmalcolega-nomehables', 120, 30, NOW(), '', 5, 5);


/* Album inserts */

insert into album(descripcion, foto, create_at, productor_id) values('el peor album de la historia', '', NOW(), 5);
insert into beat_album(album_id, beat_id) values(1, 1);
insert into beat_album(album_id, beat_id) values(1, 3);

insert into album(descripcion, foto, create_at, productor_id) values('el peor album de la historia', '', NOW(), 6);
insert into beat_album(album_id, beat_id) values(2, 4);
insert into beat_album(album_id, beat_id) values(2, 5);