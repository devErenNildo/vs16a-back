ALTER TABLE tb_user RENAME COLUMN id TO user_id;

CREATE TABLE tb_roles (
    role_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE tb_users_roles (
    user_id VARCHAR(255) NOT NULL,
    role_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES tb_user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES tb_roles(role_id) ON DELETE CASCADE
);

INSERT INTO tb_roles (role_id, name) VALUES (1, 'admin');
INSERT INTO tb_roles (role_id, name) VALUES (2, 'basic');