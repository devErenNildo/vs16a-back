# FakeBank - O banco que cobra, mas não tepaga

## Diagrama ER

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
```


## Fluxo
```mermaid
flowchart TD
    A[Usuário cria conta bancária] --> B[Conta criada com saldo inicial de R$10.000]
    B --> C[Usuário opcionalmente cadastra chave PIX]
    C --> D["Chave PIX validada - email, CPF ou celular"]

    B --> E[Usuário deseja fazer uma transferência PIX]
    E --> F[Informa valor e chave PIX do destinatário]
    F --> G[Busca conta destino pela chave PIX]
    G --> H[Verifica saldo disponível]

    H --> I{Saldo suficiente?}
    I -- Não --> J[Erro: Saldo insuficiente]
    I -- Sim --> K[Debita 100% da conta origem]
    K --> L[Aplica taxa de 15 por cento]
    L --> M[Credita 85% na conta destino]
    M --> N[Registra transação no histórico]

    N --> O[Usuário pode consultar extrato]
    O --> P[Lista transações enviadas e recebidas]
     
