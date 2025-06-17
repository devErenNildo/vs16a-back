
CREATE TABLE tb_cargos (
    id_cargo SERIAL PRIMARY KEY,
    nome VARCHAR(512) UNIQUE NOT NULL
);


INSERT INTO tb_cargos (nome) VALUES ('ROLE_ADMIN');
INSERT INTO tb_cargos (nome) VALUES ('ROLE_USUARIO');


CREATE TABLE login_cargos (
    id_login VARCHAR NOT NULL REFERENCES tb_login(id_login),
    id_cargo INTEGER NOT NULL REFERENCES tb_cargos(id_cargo),
    PRIMARY KEY (id_login, id_cargo)
);
