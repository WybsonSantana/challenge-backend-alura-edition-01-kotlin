CREATE TABLE usuario (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    password text NOT NULL,
    PRIMARY KEY(id)
);