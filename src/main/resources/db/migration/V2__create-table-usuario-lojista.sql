CREATE TABLE usuarios_logistas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    cnpj VARCHAR(14) UNIQUE,
    email VARCHAR(255) UNIQUE,
    saldo DECIMAL(19, 2)
);
