-- usuarios
INSERT INTO usuario (nome, email, senha) values ('Administrador', 'admin@os.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO usuario (nome, email, senha) values ('Funcionario 1', 'funcionario1@os.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO usuario (nome, email, senha) values ('Cliente 1', 'cliente1@email.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');

-- admin
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 1);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 3);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 4);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 5);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 6);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 7);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 8);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 9);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 10);

-- funcionario
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 1);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 3);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 4);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 5);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 7);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 8);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 9);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 10);

-- cliente
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (3, 1);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (3, 3);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (3, 4);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (3, 8);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (3, 10);

