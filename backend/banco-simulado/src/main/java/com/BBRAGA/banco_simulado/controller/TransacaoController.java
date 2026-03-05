package com.BBRAGA.banco_simulado.controller;

import com.BBRAGA.banco_simulado.dto.DepositoRequestDTO;
import com.BBRAGA.banco_simulado.dto.SaqueRequestDTO;
import com.BBRAGA.banco_simulado.dto.TransferRequestDTO;
import com.BBRAGA.banco_simulado.entity.Transacao;
import com.BBRAGA.banco_simulado.service.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("/transferir")
    public ResponseEntity<Transacao> transferir(@RequestBody TransferRequestDTO dto) {
        return ResponseEntity.ok(transacaoService.transferir(dto));
    }

    @PostMapping("/depositar")
    public ResponseEntity<Transacao> depositar(@RequestBody DepositoRequestDTO dto) {
        return ResponseEntity.ok(transacaoService.depositar(dto));
    }

    @PostMapping("/sacar")
    public ResponseEntity<Transacao> sacar(@RequestBody SaqueRequestDTO dto) {
        return ResponseEntity.ok(transacaoService.sacar(dto));
    }
}