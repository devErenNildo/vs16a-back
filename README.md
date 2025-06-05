```mermaid
erDiagram
    tb_user {
        VARCHAR user_id PK
        VARCHAR full_name
        VARCHAR email
        VARCHAR cpf
        VARCHAR celular
        VARCHAR senha
        BOOLEAN conta_confirmada
    }

    tb_account {
        INT id PK
        VARCHAR numero_conta
        VARCHAR numero_agencia
        NUMERIC saldo
        VARCHAR usuario_id FK
        TIMESTAMP criada_em
        VARCHAR chave_pix
    }

    tb_token_confirmation {
        INT id PK
        VARCHAR codigo
        VARCHAR usuario_id FK
        TIMESTAMP expiracao
    }

    tb_transacao {
        INT id PK
        INT conta_origem_id FK
        INT conta_destino_id FK
        NUMERIC valor
        TIMESTAMP data_hora
        VARCHAR descricao
    }

    tb_roles {
        INT role_id PK
        VARCHAR name
    }

    tb_users_roles {
        VARCHAR user_id FK
        INT role_id FK
    }

    tb_user ||--o{ tb_account : "possui"
    tb_user ||--o{ tb_token_confirmation : "tem"
    tb_user ||--o{ tb_users_roles : "tem"
    tb_roles ||--o{ tb_users_roles : "atribuída"
    tb_account ||--o{ tb_transacao : "origem"
    tb_account ||--o{ tb_transacao : "destino"

