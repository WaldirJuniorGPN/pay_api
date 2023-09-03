package com.paySimplificado.pay_api.dto.reponse;

import com.paySimplificado.pay_api.model.UsuarioLojista;

public record DadosDetalhamentoUsuarioLojista(Long id, String nome, String email, String cnpj) {
    public DadosDetalhamentoUsuarioLojista(UsuarioLojista usuarioLojista) {
        this(usuarioLojista.getId(), usuarioLojista.getNome(), usuarioLojista.getEmail(), usuarioLojista.getCnpj());
    }
}
