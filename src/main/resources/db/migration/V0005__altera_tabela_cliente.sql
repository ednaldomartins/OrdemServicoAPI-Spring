alter table cliente
    add column logradouro varchar(60) not null,
    add column numero varchar(8) not null,
    add column complemento varchar(60),
    add column bairro varchar(60) not null,
    add column cep varchar(10) not null,
    add column cidade varchar(60) not null,
    add column estado varchar(2) not null
    after fone
