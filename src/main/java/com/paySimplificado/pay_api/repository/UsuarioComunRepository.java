package com.paySimplificado.pay_api.repository;

import com.paySimplificado.pay_api.model.UsuarioComum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioComunRepository extends JpaRepository<UsuarioComum, Long> {
}
