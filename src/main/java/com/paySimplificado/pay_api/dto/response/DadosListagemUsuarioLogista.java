package com.paySimplificado.pay_api.dto.response;

import com.paySimplificado.pay_api.model.UsuarioLogista;

public record DadosListagemUsuarioLogista(Long id, String nomeCompleto, String email, String cnpj) {
    public DadosListagemUsuarioLogista(UsuarioLogista usuarioLogista) {
        this(usuarioLogista.getId(), usuarioLogista.getNomeCompleto(), usuarioLogista.getEmail(), usuarioLogista.getCnpj());
    }
}
