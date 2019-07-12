CREATE TABLE categoria(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) VALUE ('Lazer');
INSERT INTO categoria (nome) VALUE ('Alimentação');
INSERT INTO categoria (nome) VALUE ('Supermercado');
INSERT INTO categoria (nome) VALUE ('Farmácia');
INSERT INTO categoria (nome) VALUE ('Outros');