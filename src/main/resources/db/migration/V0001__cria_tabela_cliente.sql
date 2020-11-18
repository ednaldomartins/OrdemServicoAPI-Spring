create table cliente (
	id bigint not null auto_increment,
  	nome varchar(60) not null,
  	email varchar(255) not null,
  	telefone varchar(20) not null,
	logradouro varchar(60) not null,
	numero varchar(8) not null,
	complemento varchar(60),
	bairro varchar(60) not null,
	cep varchar(10) not null,
	cidade varchar(60) not null,
	estado varchar(2) not null,
    
  	primary key(id)
);
