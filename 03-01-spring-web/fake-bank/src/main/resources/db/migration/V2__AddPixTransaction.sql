ALTER TABLE tb_account
    ADD COLUMN chave_pix VARCHAR(255) UNIQUE;

CREATE TABLE tb_transacao (
    id SERIAL PRIMARY KEY,
    conta_origem_id BIGINT NOT NULL,
    conta_destino_id BIGINT NOT NULL,
    valor NUMERIC(15, 2) NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    descricao VARCHAR(255) NOT NULL,

    CONSTRAINT fk_transacao_origem FOREIGN KEY (conta_origem_id)
      REFERENCES tb_account(id) ON DELETE CASCADE,

    CONSTRAINT fk_transacao_destino FOREIGN KEY (conta_destino_id)
      REFERENCES tb_account(id) ON DELETE CASCADE
);
