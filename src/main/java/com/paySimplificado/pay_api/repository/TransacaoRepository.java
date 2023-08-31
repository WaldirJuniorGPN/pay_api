package com.paySimplificado.pay_api.repository;

import com.paySimplificado.pay_api.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
