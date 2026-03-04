package com.BBRAGA.banco_simulado.entity;

import com.BBRAGA.banco_simulado.enums.StatusTransacao;
import com.BBRAGA.banco_simulado.enums.TipoTransacao;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "transacao")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Conta contaOrigem;

    @ManyToOne(fetch = FetchType.LAZY)
    private Conta contaDestino; // null para depósito/saque

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoTransacao tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusTransacao status = StatusTransacao.SUCESSO;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private OffsetDateTime criadoEm = OffsetDateTime.now();

    @Column(length = 255)
    private String mensagem;

    // getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conta getContaOrigem(){
        return contaOrigem;
    }

    public void setContaOrigem(Conta contaOrigem){
        this.contaOrigem = contaOrigem;
    }

    public Conta getContaDestino(){
        return contaDestino;
    }

    public void setContaDestino(Conta contaDestino){
        this.contaDestino = contaDestino;
    }

    public TipoTransacao getTipo(){
        return tipo;
    }

    public void setTipo(TipoTransacao tipo){
        this.tipo = tipo;
    }

    public StatusTransacao getStatusTransacao(){
        return status;
    }

    public void setStatus(StatusTransacao status){
        this.status = status;
    }

    public BigDecimal getValor(){
        return valor;
    }

    public void setValor(BigDecimal valor){
        this.valor = valor;
    }

    public OffsetDateTime getCriadoEm(){
        return criadoEm;
    }

    public void setCriadoEm(OffsetDateTime criadoEm){
        this.criadoEm = criadoEm;
    }

    public String getMensagem(){
        return mensagem;
    }

    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }
}