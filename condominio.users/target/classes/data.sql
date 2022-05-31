insert into user_app (id, created_at, email, last_name, name, status, dni) VALUES (1, now(), 'sofia.arevalor@gmail.com', 'arevalo', 'sofia', 'ENABLE', '19456195-4');
insert into user_app (id, created_at, email, last_name, name, status, dni) VALUES (2, now(), 'davidparraz41@gmail.com', 'parra', 'david', 'ENABLE', '17461331-1');

insert into role (id, name) VALUES (1, 'residente');
insert into role (id, name) VALUES (2, 'administrador');
insert into role (id, name) VALUES (3, 'conserje');

insert into users_roles (user_id, role_id) VALUES (1, 1);
insert into users_roles (user_id, role_id) VALUES (1, 2);
insert into users_roles (user_id, role_id) VALUES (2, 1);