package com.paySimplificado.pay_api.dto.reponse;

import com.paySimplificado.pay_api.model.UsuarioComum;

public record DadosDetalhamentoUsuarioComum(Long id, String nome, String email, String cpf) {
    public DadosDetalhamentoUsuarioComum(UsuarioComum usuarioComum) {
        this(usuarioComum.getId(), usuarioComum.getNome(), usuarioComum.getEmail(), usuarioComum.getCpf());
    }
}
