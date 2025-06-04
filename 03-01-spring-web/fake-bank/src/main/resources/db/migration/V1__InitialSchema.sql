CREATE TABLE tb_user (
     id VARCHAR(255) PRIMARY KEY,
     full_name VARCHAR(255) NOT NULL,
     email VARCHAR(255) NOT NULL UNIQUE,
     cpf VARCHAR(11) NOT NULL UNIQUE,
     celular VARCHAR(11) NOT NULL UNIQUE,
     senha VARCHAR(255) NOT NULL,
     conta_confirmada BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE tb_account (
    id SERIAL PRIMARY KEY,
    numero_conta VARCHAR(255) NOT NULL UNIQUE,
    numero_agencia VARCHAR(255) NOT NULL UNIQUE,
    saldo NUMERIC(19, 2) NOT NULL,
    usuario_id VARCHAR(255) NOT NULL UNIQUE,
    criada_em TIMESTAMP NOT NULL,
    CONSTRAINT fk_account_user FOREIGN KEY (usuario_id) REFERENCES tb_user(id)
);

CREATE TABLE tb_token_confirmation (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(6) NOT NULL,
    usuario_id VARCHAR(255) NOT NULL UNIQUE,
    expiracao TIMESTAMP NOT NULL,
    CONSTRAINT fk_token_user FOREIGN KEY (usuario_id) REFERENCES tb_user(id)
);


