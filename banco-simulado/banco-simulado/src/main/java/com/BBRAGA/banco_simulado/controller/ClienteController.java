package com.BBRAGA.banco_simulado.controller;

import com.BBRAGA.banco_simulado.dto.ClienteRequest;
import com.BBRAGA.banco_simulado.entity.Cliente;
import com.BBRAGA.banco_simulado.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody ClienteRequest req) {
        Cliente salvo = clienteService.criar(req);
        return ResponseEntity.ok(salvo);
    }
}