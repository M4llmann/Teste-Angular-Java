-- Criando a tabela Contas
CREATE TABLE Contas (
    ContaID SERIAL PRIMARY KEY, -- SERIAL cria um identificador único auto-incremental
    nomeTitular VARCHAR(100),
    saldo NUMERIC(10,2), -- NUMERIC é ideal para valores decimais precisos
    status BOOLEAN DEFAULT TRUE, -- Status inicial como ativo
    dataCriacao TIMESTAMP DEFAULT NOW() -- Data e hora de criação atual
);

-- Criando a tabela Transacoes
CREATE TABLE Transacoes (
    TransacaoID SERIAL PRIMARY KEY,
    idConta INTEGER REFERENCES Contas(ContaID), -- Chave estrangeira referenciando a tabela Contas
    tipo VARCHAR(50),
    valor NUMERIC(10,2),
    dataTransacao TIMESTAMP DEFAULT NOW()
);