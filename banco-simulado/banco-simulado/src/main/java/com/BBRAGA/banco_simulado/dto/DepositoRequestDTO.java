package com.BBRAGA.banco_simulado.dto;

import java.math.BigDecimal;

public class DepositoRequestDTO {
    private String contaOrigem;   // obrigatório
    private String contaDestino;  // obrigatório
    private BigDecimal valor;     // > 0

    public String getContaOrigem() { return contaOrigem; }
    public void setContaOrigem(String contaOrigem) { this.contaOrigem = contaOrigem; }
    public String getContaDestino() { return contaDestino; }
    public void setContaDestino(String contaDestino) { this.contaDestino = contaDestino; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
}