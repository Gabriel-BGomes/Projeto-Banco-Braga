package com.BBRAGA.banco_simulado.dto;

import java.math.BigDecimal;

public class SaqueRequestDTO {
    private String contaOrigem;
    private BigDecimal valor;

    public String getContaOrigem() { return contaOrigem; }
    public void setContaOrigem(String contaOrigem) { this.contaOrigem = contaOrigem; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
}