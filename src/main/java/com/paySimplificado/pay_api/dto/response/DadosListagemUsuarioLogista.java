package com.paySimplificado.pay_api.dto.response;

import com.paySimplificado.pay_api.model.UsuarioLojista;

public record DadosListagemUsuarioLogista(Long id, String nomeCompleto, String email, String cnpj) {
    public DadosListagemUsuarioLogista(UsuarioLojista usuarioLojista) {
        this(usuarioLojista.getId(), usuarioLojista.getNomeCompleto(), usuarioLojista.getEmail(), usuarioLojista.getCnpj());
    }
}
