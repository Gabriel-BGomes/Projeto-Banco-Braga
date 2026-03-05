package com.BBRAGA.banco_simulado.repository;

import com.BBRAGA.banco_simulado.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}