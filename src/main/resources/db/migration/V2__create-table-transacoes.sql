CREATE TABLE transacoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_origem_id BIGINT,
    usuario_destino_id BIGINT,
    valor_da_operacao DECIMAL(19, 2),
    FOREIGN KEY (usuario_origem_id) REFERENCES usuarios (id),
    FOREIGN KEY (usuario_destino_id) REFERENCES usuarios (id)
);
