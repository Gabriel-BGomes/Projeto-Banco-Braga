package com.BBRAGA.banco_simulado.service;

import com.BBRAGA.banco_simulado.dto.ContaAberturaRequest;
import com.BBRAGA.banco_simulado.entity.Cliente;
import com.BBRAGA.banco_simulado.entity.Conta;
import com.BBRAGA.banco_simulado.enums.StatusConta;
import com.BBRAGA.banco_simulado.enums.TipoConta;
import com.BBRAGA.banco_simulado.repository.ClienteRepository;
import com.BBRAGA.banco_simulado.repository.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Conta abrirConta(ContaAberturaRequest req) {
        if (req.getDepositoInicial() != null && req.getDepositoInicial().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Depósito inicial não pode ser negativo");
        }

        Cliente cliente = clienteRepository.findById(req.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        

        Conta conta = new Conta();
        conta.setCliente(cliente);
        conta.setAgencia(req.getAgencia());
        conta.setTipo(TipoConta.valueOf(req.getTipo()));
        conta.setStatus(StatusConta.ATIVA);
        conta.setNumero(gerarNumeroConta());
        conta.setSaldo(req.getDepositoInicial() != null ? req.getDepositoInicial() : BigDecimal.ZERO);

        return contaRepository.save(conta);
    }

    private String gerarNumeroConta() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
}