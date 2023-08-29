package com.paySimplificado.pay_api.dto.response;

import com.paySimplificado.pay_api.model.UsuarioComum;

public record DadosDetalhamentoUsuarioComum(Long id, String nomeCompleto, String email, String cpf) {
    public DadosDetalhamentoUsuarioComum(UsuarioComum usuarioComum) {
        this(usuarioComum.getId(), usuarioComum.getNomeCompleto(), usuarioComum.getEmail(), usuarioComum.getCpf());
    }
}
