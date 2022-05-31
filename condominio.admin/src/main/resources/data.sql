insert into condominium (id, address, name) VALUES (1, 'Los presidentes 119', 'Condominio 1');
insert into condominium (id, address, name) VALUES (2, 'Av Ossa 2345', 'Condominio Dso');
insert into condominium (id, address, name) VALUES (3, 'Los Orientales 4433', 'Condominio Tres');

insert into tower (id, identification, condominium_id) VALUES (1, 'torre a', 1);
insert into tower (id, identification, condominium_id) VALUES (2, 'torre b', 1);
insert into tower (id, identification, condominium_id) VALUES (3, 'torre c', 1);

insert into department (id, number, tower_id, user_id) VALUES (1, '1', 1, 1);
insert into department (id, number, tower_id, user_id) VALUES (2, '2', 1, 2);
insert into department (id, number, tower_id, user_id) VALUES (3, '2', 1, null);
