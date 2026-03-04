-- Clientes
CREATE TABLE cliente (
  id              BIGSERIAL PRIMARY KEY,
  nome            VARCHAR(150) NOT NULL,
  cpf             VARCHAR(14) UNIQUE NOT NULL,
  email           VARCHAR(150) NOT NULL,
  data_nascimento DATE NOT NULL,
  criado_em       TIMESTAMP NOT NULL DEFAULT now(),
  atualizado_em   TIMESTAMP NOT NULL DEFAULT now()
);

-- Contas
CREATE TABLE conta (
  id             BIGSERIAL PRIMARY KEY,
  numero         VARCHAR(20) UNIQUE NOT NULL,
  agencia        VARCHAR(10) NOT NULL,
  saldo          NUMERIC(18,2) NOT NULL DEFAULT 0,
  tipo           VARCHAR(20) NOT NULL, -- CORRENTE | POUPANCA
  status         VARCHAR(20) NOT NULL DEFAULT 'ATIVA',
  cliente_id     BIGINT NOT NULL REFERENCES cliente(id),
  criado_em      TIMESTAMP NOT NULL DEFAULT now(),
  atualizado_em  TIMESTAMP NOT NULL DEFAULT now()
);

-- Transações
CREATE TABLE transacao (
  id               BIGSERIAL PRIMARY KEY,
  conta_origem_id  BIGINT NOT NULL REFERENCES conta(id),
  conta_destino_id BIGINT REFERENCES conta(id),
  tipo             VARCHAR(20) NOT NULL, -- DEPOSITO | SAQUE | TRANSFERENCIA
  valor            NUMERIC(18,2) NOT NULL,
  status           VARCHAR(20) NOT NULL DEFAULT 'SUCESSO',
  criado_em        TIMESTAMP NOT NULL DEFAULT now()
);