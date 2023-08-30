package com.paySimplificado.pay_api.dto.response;

import com.paySimplificado.pay_api.model.UsuarioLogista;

public record DadosDetalhamentoUsuarioLogista(Long id, String nomeCompleto, String email, String cnpj) {
    public DadosDetalhamentoUsuarioLogista(UsuarioLogista usuarioLogista){
        this(usuarioLogista.getId(), usuarioLogista.getNomeCompleto(), usuarioLogista.getEmail(), usuarioLogista.getCnpj());
    }
}
