package com.BBRAGA.banco_simulado.service;

import com.BBRAGA.banco_simulado.dto.DepositoRequestDTO;
import com.BBRAGA.banco_simulado.dto.SaqueRequestDTO;
import com.BBRAGA.banco_simulado.dto.TransferRequestDTO;
import com.BBRAGA.banco_simulado.entity.Conta;
import com.BBRAGA.banco_simulado.entity.Transacao;
import com.BBRAGA.banco_simulado.enums.StatusConta;
import com.BBRAGA.banco_simulado.enums.StatusTransacao;
import com.BBRAGA.banco_simulado.enums.TipoTransacao;
import com.BBRAGA.banco_simulado.repository.ContaRepository;
import com.BBRAGA.banco_simulado.repository.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransacaoService {

    private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;

    public TransacaoService(ContaRepository contaRepository, TransacaoRepository transacaoRepository) {
        this.contaRepository = contaRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @Transactional
    public Transacao transferir(TransferRequestDTO dto) {
        BigDecimal valor = dto.getValor();
        validarValor(valor);

        Conta origem = contaRepository.findByNumero(dto.getContaOrigem())
                .orElseThrow(() -> new IllegalArgumentException("Conta origem não encontrada"));
        Conta destino = contaRepository.findByNumero(dto.getContaDestino())
                .orElseThrow(() -> new IllegalArgumentException("Conta destino não encontrada"));

        validarContaAtiva(origem);
        validarContaAtiva(destino);
        if (origem.getNumero().equals(destino.getNumero())) {
            throw new IllegalArgumentException("Conta origem e destino não podem ser iguais");
        }
        if (origem.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        origem.setSaldo(origem.getSaldo().subtract(valor));
        destino.setSaldo(destino.getSaldo().add(valor));
        contaRepository.save(origem);
        contaRepository.save(destino);

        Transacao tx = new Transacao();
        tx.setContaOrigem(origem);
        tx.setContaDestino(destino);
        tx.setTipo(TipoTransacao.TRANSFERENCIA);
        tx.setValor(valor);
        tx.setStatus(StatusTransacao.SUCESSO);
        return transacaoRepository.save(tx);
    }

    @Transactional
    public Transacao depositar(DepositoRequestDTO dto) {
        BigDecimal valor = dto.getValor();
        validarValor(valor);

        Conta origem = contaRepository.findByNumero(dto.getContaOrigem())
                .orElseThrow(() -> new IllegalArgumentException("Conta origem não encontrada"));
        Conta destino = contaRepository.findByNumero(dto.getContaDestino())
                .orElseThrow(() -> new IllegalArgumentException("Conta destino não encontrada"));

        validarContaAtiva(origem);
        validarContaAtiva(destino);

        if (origem.getNumero().equals(destino.getNumero())) {
            throw new IllegalArgumentException("Conta origem e destino não podem ser iguais");
        }
        if (origem.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente na conta origem");
        }

        // débito na origem, crédito na destino
        origem.setSaldo(origem.getSaldo().subtract(valor));
        destino.setSaldo(destino.getSaldo().add(valor));
        contaRepository.save(origem);
        contaRepository.save(destino);

        Transacao tx = new Transacao();
        tx.setContaOrigem(origem);
        tx.setContaDestino(destino);
        tx.setTipo(TipoTransacao.DEPOSITO);
        tx.setValor(valor);
        tx.setStatus(StatusTransacao.SUCESSO);
        return transacaoRepository.save(tx);
    }

    @Transactional
    public Transacao sacar(SaqueRequestDTO dto) {
        BigDecimal valor = dto.getValor();
        validarValor(valor);

        Conta origem = contaRepository.findByNumero(dto.getContaOrigem())
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));

        validarContaAtiva(origem);
        if (origem.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        origem.setSaldo(origem.getSaldo().subtract(valor));
        contaRepository.save(origem);

        Transacao tx = new Transacao();
        tx.setContaOrigem(origem);
        tx.setTipo(TipoTransacao.SAQUE);
        tx.setValor(valor);
        tx.setStatus(StatusTransacao.SUCESSO);
        return transacaoRepository.save(tx);
    }

    private void validarValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
    }

    private void validarContaAtiva(Conta conta) {
        if (conta.getStatus() != StatusConta.ATIVA) {
            throw new IllegalStateException("Conta inativa/bloqueada: " + conta.getNumero());
        }
    }
}