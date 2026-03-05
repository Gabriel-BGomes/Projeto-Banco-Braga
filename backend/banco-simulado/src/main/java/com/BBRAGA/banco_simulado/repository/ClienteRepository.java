package com.BBRAGA.banco_simulado.repository;

import com.BBRAGA.banco_simulado.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}