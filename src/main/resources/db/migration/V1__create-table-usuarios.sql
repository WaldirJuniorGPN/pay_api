CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nomeCompleto VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    senha VARCHAR(255),
    carteira DECIMAL(19, 2),
    tipo_usuario VARCHAR(255),
    cpf VARCHAR(14) UNIQUE,
    cnpj VARCHAR(18) UNIQUE,
    CONSTRAINT chk_tipo_usuario CHECK (tipo_usuario IN ('comum', 'logista'))
);
