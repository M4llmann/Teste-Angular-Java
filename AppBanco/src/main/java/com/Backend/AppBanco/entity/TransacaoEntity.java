package com.Backend.AppBanco.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Transacao")
public class TransacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTransacao")
    private Integer idTransacao;

    @ManyToOne
    @JoinColumn(name = "idConta", referencedColumnName = "idConta", nullable = false)
    private ContaEntity conta;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "dataTransacao", nullable = false)
    private LocalDateTime dataTransacao;

    public TransacaoEntity(){
        
    }

    public TransacaoEntity(String tipo, BigDecimal valor, ContaEntity conta) {
        this.tipo = tipo;
        this.valor = valor;
        this.conta = conta;
        this.dataTransacao = LocalDateTime.now(); // Define a data atual para dataTransacao
    }

    // Getter e Setter para idTransacao
    public Integer getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Integer idTransacao) {
        this.idTransacao = idTransacao;
    }

    // Getter e Setter para conta
    public ContaEntity getConta() {
        return conta;
    }

    public void setConta(ContaEntity conta) {
        this.conta = conta;
    }

    // Getter e Setter para tipo
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Getter e Setter para valor
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    // Getter e Setter para dataTransacao
    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
}
