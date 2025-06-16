CREATE TABLE tb_user (
    id_user VARCHAR(255) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    data_cadastro TIMESTAMP,
    conta_confirmada BOOLEAN DEFAULT FALSE
);

CREATE SEQUENCE seq_conta_bancaria START 1 INCREMENT 1;

CREATE TABLE tb_conta_bancaria (
    id_conta_bancaria BIGINT PRIMARY KEY DEFAULT nextval('seq_conta_bancaria'),
    nome_banco VARCHAR(255) NOT NULL,
    id_user VARCHAR(255),
    CONSTRAINT fk_user_conta FOREIGN KEY (id_user) REFERENCES tb_user (id_user)
);

CREATE SEQUENCE seq_cartao_credito START 1 INCREMENT 1;

CREATE TABLE tb_cartao_credito (
    id_cartao BIGINT PRIMARY KEY DEFAULT nextval('seq_cartao_credito'),
    nome_cartao VARCHAR(255),
    limite NUMERIC,
    dia_fechamento_fatura INTEGER,
    dia_vencimento_fatura INTEGER,
    id_conta_bancaria BIGINT,
    CONSTRAINT fk_conta_cartao FOREIGN KEY (id_conta_bancaria) REFERENCES tb_conta_bancaria (id_conta_bancaria)
);

CREATE SEQUENCE seq_emprestimo START 1 INCREMENT 1;

CREATE TABLE tb_emprestimo (
    id_emprestimo BIGINT PRIMARY KEY DEFAULT nextval('seq_emprestimo'),
    descricao VARCHAR(255),
    valor_total NUMERIC,
    valor_parcela NUMERIC,
    quantidade_parcelas INTEGER,
    data_inicio DATE,
    data_fim DATE,
    id_conta_bancaria BIGINT,
    CONSTRAINT fk_conta_emprestimo FOREIGN KEY (id_conta_bancaria) REFERENCES tb_conta_bancaria (id_conta_bancaria)
);

CREATE SEQUENCE seq_conta_mensal START 1 INCREMENT 1;

CREATE TABLE tb_conta_mensal (
    id_conta_mensal BIGINT PRIMARY KEY DEFAULT nextval('seq_conta_mensal'),
    valor NUMERIC,
    mes DATE,
    id_user VARCHAR(255),
    CONSTRAINT fk_user_conta_mensal FOREIGN KEY (id_user) REFERENCES tb_user (id_user)
);

CREATE SEQUENCE seq_boleto START 1 INCREMENT 1;

CREATE TABLE tb_boleto (
    id BIGINT PRIMARY KEY DEFAULT nextval('seq_boleto'),
    descricao VARCHAR(255),
    valor NUMERIC,
    data_vencimento DATE,
    data_pagamento DATE,
    tipo_servico VARCHAR(255),
    id_user VARCHAR(255),
    CONSTRAINT fk_user_boleto FOREIGN KEY (id_user) REFERENCES tb_user (id_user)
);

CREATE TABLE tb_login (
    id_login VARCHAR(255) PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    id_user VARCHAR(255) NOT NULL UNIQUE,
    CONSTRAINT fk_user_login FOREIGN KEY (id_user) REFERENCES tb_user (id_user)
);

CREATE SEQUENCE seq_token_confirmacao START 1 INCREMENT 1;

CREATE TABLE tb_token_confirmacao (
    id_token_confirmacao BIGINT PRIMARY KEY DEFAULT nextval('seq_token_confirmacao'),
    codigo VARCHAR(255),
    id_user VARCHAR(255),
    expiracao TIMESTAMP,
    CONSTRAINT fk_user_token FOREIGN KEY (id_user) REFERENCES tb_user (id_user)
);







