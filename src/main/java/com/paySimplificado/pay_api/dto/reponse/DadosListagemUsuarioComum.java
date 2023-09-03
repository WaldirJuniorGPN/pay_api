package com.paySimplificado.pay_api.dto.reponse;

import com.paySimplificado.pay_api.model.UsuarioComum;

import java.math.BigDecimal;

public record DadosListagemUsuarioComum(Long id, String nome, String email, String cpf, BigDecimal saldo) {
    public DadosListagemUsuarioComum(UsuarioComum usuarioComum) {
        this(usuarioComum.getId(), usuarioComum.getNome(), usuarioComum.getEmail(), usuarioComum.getCpf(), new BigDecimal(usuarioComum.getSaldo().toString()));
    }
}
