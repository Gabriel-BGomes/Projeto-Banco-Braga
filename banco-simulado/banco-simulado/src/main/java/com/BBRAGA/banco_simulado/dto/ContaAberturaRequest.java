package com.BBRAGA.banco_simulado.dto;

import java.math.BigDecimal;

public class ContaAberturaRequest {
    private Long clienteId;
    private String agencia;
    private String tipo; // CORRENTE ou POUPANCA
    private BigDecimal depositoInicial;

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public BigDecimal getDepositoInicial() { return depositoInicial; }
    public void setDepositoInicial(BigDecimal depositoInicial) { this.depositoInicial = depositoInicial; }
}