package com.Backend.AppBanco.dto;

import java.math.BigDecimal;

public class TransacaoDTO {
    private BigDecimal valor;

    // Getters and Setters
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
