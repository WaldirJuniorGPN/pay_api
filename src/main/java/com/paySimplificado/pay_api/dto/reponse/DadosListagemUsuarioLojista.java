package com.paySimplificado.pay_api.dto.reponse;

import com.paySimplificado.pay_api.model.UsuarioLojista;

import java.math.BigDecimal;

public record DadosListagemUsuarioLojista(Long id, String nome, String email, String cnpj, BigDecimal saldo) {
    public DadosListagemUsuarioLojista(UsuarioLojista usuarioLojista) {
        this(usuarioLojista.getId(), usuarioLojista.getNome(), usuarioLojista.getEmail(), usuarioLojista.getCnpj(), new BigDecimal(usuarioLojista.getSaldo().toString()));
    }
}
