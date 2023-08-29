package com.paySimplificado.pay_api.dto.response;

import com.paySimplificado.pay_api.model.UsuarioComum;

public record DadosListagemUsuarioComum(Long id, String nomeCompleto, String email, String cpf) {
    public DadosListagemUsuarioComum(UsuarioComum usuarioComum) {
        this(usuarioComum.getId(), usuarioComum.getNomeCompleto(), usuarioComum.getEmail(), usuarioComum.getCpf());
    }
}
