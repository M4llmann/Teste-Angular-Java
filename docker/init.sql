-- Script para criação das tabelas

-- Tabela Usuario
CREATE TABLE IF NOT EXISTS Usuario (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- Tabela Conta
CREATE TABLE IF NOT EXISTS Conta (
    idConta SERIAL PRIMARY KEY,
    nomeTitular VARCHAR(255) NOT NULL,
    saldo DECIMAL(15, 2) NOT NULL,
    status BOOLEAN NOT NULL,
    dataCriacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idUsuario INTEGER,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(id)  -- Relacionamento com a tabela Usuario
);

-- Tabela Transacao
CREATE TABLE IF NOT EXISTS Transacao (
    idTransacao SERIAL PRIMARY KEY,
    idConta INTEGER NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    valor DECIMAL(15, 2) NOT NULL,
    dataTransacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idConta) REFERENCES Conta(idConta)  -- Relacionamento com a tabela Conta
);
