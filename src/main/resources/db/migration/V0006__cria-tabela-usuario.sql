CREATE TABLE usuario (
	id BIGINT(20) NOT NULL auto_increment,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL,
	
    	primary key (id)
);

