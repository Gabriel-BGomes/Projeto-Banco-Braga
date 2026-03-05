package com.BBRAGA.banco_simulado.controller;

import com.BBRAGA.banco_simulado.dto.ContaAberturaRequest;
import com.BBRAGA.banco_simulado.entity.Conta;
import com.BBRAGA.banco_simulado.service.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<Conta> abrirConta(@RequestBody ContaAberturaRequest request) {
        Conta conta = contaService.abrirConta(request);
        return ResponseEntity.ok(conta); // em produção, devolva um DTO de resposta
    }
}