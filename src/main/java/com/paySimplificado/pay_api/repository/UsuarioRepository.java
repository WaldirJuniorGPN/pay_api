package com.paySimplificado.pay_api.repository;

import com.paySimplificado.pay_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
