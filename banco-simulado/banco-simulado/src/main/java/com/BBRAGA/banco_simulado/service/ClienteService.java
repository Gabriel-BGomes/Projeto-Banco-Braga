package com.BBRAGA.banco_simulado.service;

import com.BBRAGA.banco_simulado.dto.ClienteRequest;
import com.BBRAGA.banco_simulado.entity.Cliente;
import com.BBRAGA.banco_simulado.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente criar(ClienteRequest req) {
        if (req.getNome() == null || req.getNome().isBlank()) throw new IllegalArgumentException("Nome obrigatório");
        if (req.getCpf() == null || req.getCpf().isBlank()) throw new IllegalArgumentException("CPF obrigatório");
        if (req.getEmail() == null || req.getEmail().isBlank()) throw new IllegalArgumentException("Email obrigatório");
        if (req.getDataNascimento() == null) throw new IllegalArgumentException("Data de nascimento obrigatória");

        // regra simples: não permitir CPF duplicado
        clienteRepository.findAll().stream()
                .filter(c -> c.getCpf().equals(req.getCpf()))
                .findFirst()
                .ifPresent(c -> { throw new IllegalArgumentException("CPF já cadastrado"); });

        Cliente c = new Cliente();
        c.setNome(req.getNome());
        c.setCpf(req.getCpf());
        c.setEmail(req.getEmail());
        c.setDataNascimento(req.getDataNascimento());
        return clienteRepository.save(c);
    }
}