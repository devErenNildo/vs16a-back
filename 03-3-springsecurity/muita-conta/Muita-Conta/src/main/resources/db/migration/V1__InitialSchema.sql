CREATE TABLE tb_user (
    id_user VARCHAR PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255),
    data_cadastro TIMESTAMP,
    conta_confirmada BOOLEAN
);

CREATE SEQUENCE seq_cartao_credito START 1 INCREMENT 1;

CREATE TABLE tb_cartao_credito (
    id_cartao BIGINT PRIMARY KEY DEFAULT nextval('seq_cartao_credito'),
    nome_cartao VARCHAR(255),
    limite NUMERIC,
    dia_fechamento_fatura INTEGER,
    dia_vencimento_fatura INTEGER,
    id_user VARCHAR REFERENCES tb_user(id_user)
);

CREATE SEQUENCE seq_gasto_cartao START 1 INCREMENT 1;

CREATE TABLE tb_gstos_cartao (
    id_gasto_cartao BIGINT PRIMARY KEY DEFAULT nextval('seq_gasto_cartao'),
    descricao VARCHAR(255),
    valor_total NUMERIC,
    quantidade_parcelas INTEGER,
    data_compra DATE,
    id_cartao BIGINT REFERENCES tb_cartao_credito(id_cartao)
);

CREATE SEQUENCE seq_parcela_cartao START 1 INCREMENT 1;

CREATE TABLE tb_parcela_cartao (
    id_parcela_cartao BIGINT PRIMARY KEY DEFAULT nextval('seq_parcela_cartao'),
    valor_parcela NUMERIC,
    competencia VARCHAR(7),
    id_gasto_cartao BIGINT REFERENCES tb_gstos_cartao(id_gasto_cartao)
);

CREATE SEQUENCE seq_conta_mensal START 1 INCREMENT 1;

CREATE TABLE tb_conta_mensal (
    id_conta_mensal BIGINT PRIMARY KEY DEFAULT nextval('seq_conta_mensal'),
    valor NUMERIC,
    mes DATE,
    id_user VARCHAR REFERENCES tb_user(id_user)
);

CREATE SEQUENCE seq_boleto START 1 INCREMENT 1;

CREATE TABLE tb_boleto (
    id BIGINT PRIMARY KEY DEFAULT nextval('seq_boleto'),
    descricao VARCHAR(255),
    valor NUMERIC,
    data_vencimento DATE,
    data_pagamento DATE,
    tipo_servico VARCHAR(255),
    id_user VARCHAR REFERENCES tb_user(id_user)
);

CREATE SEQUENCE seq_gasto_avulso START 1 INCREMENT 1;

CREATE TABLE tb_gasto_avulso (
    id BIGINT PRIMARY KEY DEFAULT nextval('seq_gasto_avulso'),
    descricao VARCHAR(255),
    valor NUMERIC,
    data_gasto DATE,
    id_user VARCHAR REFERENCES tb_user(id_user)
);

CREATE SEQUENCE seq_emprestimo START 1 INCREMENT 1;

CREATE TABLE tb_emprestimo (
    id_emprestimo BIGINT PRIMARY KEY DEFAULT nextval('seq_emprestimo'),
    descricao VARCHAR(255),
    valor_total NUMERIC,
    valor_parcela NUMERIC,
    quantidade_parcelas INTEGER,
    data_inicio DATE,
    data_fim DATE
);

CREATE TABLE tb_login (
    id_login VARCHAR PRIMARY KEY,
    login VARCHAR(255),
    senha VARCHAR(255),
    id_user VARCHAR NOT NULL REFERENCES tb_user(id_user)
);

CREATE SEQUENCE seq_token_confirmacao START 1 INCREMENT 1;

CREATE TABLE tb_token_confirmacao (
    id_token_confirmacao BIGINT PRIMARY KEY DEFAULT nextval('seq_token_confirmacao'),
    codigo VARCHAR(255),
    id_user VARCHAR REFERENCES tb_user(id_user),
    expiracao TIMESTAMP
);
