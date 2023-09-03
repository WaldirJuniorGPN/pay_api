package com.paySimplificado.pay_api.dto.response;

import com.paySimplificado.pay_api.model.UsuarioLojista;

public record DadosDetalhamentoUsuarioLogista(Long id, String nomeCompleto, String email, String cnpj) {
    public DadosDetalhamentoUsuarioLogista(UsuarioLojista usuarioLojista){
        this(usuarioLojista.getId(), usuarioLojista.getNomeCompleto(), usuarioLojista.getEmail(), usuarioLojista.getCnpj());
    }
}
