package com.Backend.AppBanco.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ContaDTO {
    private Integer idConta;  // Este será gerado automaticamente
    private String nomeTitular;
    private BigDecimal saldo;  // Será configurado no backend como BigDecimal.ZERO
    private Boolean status;  // Este é o campo que o frontend envia
    private LocalDateTime dataCriacao;  // Será configurado no backend

    public ContaDTO() {}

    public ContaDTO(Integer idConta, String nomeTitular, BigDecimal saldo, Boolean status, LocalDateTime dataCriacao) {
        this.idConta = idConta;
        this.nomeTitular = nomeTitular;
        this.saldo = saldo;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    // Getters e Setters
    public Integer getIdConta() { return idConta; }
    public void setIdConta(Integer idConta) { this.idConta = idConta; }
    public String getNomeTitular() { return nomeTitular; }
    public void setNomeTitular(String nomeTitular) { this.nomeTitular = nomeTitular; }
    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
}
