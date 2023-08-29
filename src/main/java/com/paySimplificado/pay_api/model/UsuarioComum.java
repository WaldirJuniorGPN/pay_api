package com.paySimplificado.pay_api.model;

import com.paySimplificado.pay_api.dto.request.DadosAtualizacaoUsuarioComum;
import com.paySimplificado.pay_api.dto.request.DadosCadastroUsuarioComum;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "UsuarioComum")
@DiscriminatorValue("comum")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UsuarioComum extends Usuario {

    @Column(unique = true)
    private String cpf;

    public UsuarioComum(DadosCadastroUsuarioComum dados) {
        super.setNomeCompleto(dados.nomeCompleto());
        super.setEmail(dados.email());
        super.setSenha(dados.senha());
        this.cpf = dados.cpf();
    }

    public void atualizarDados(DadosAtualizacaoUsuarioComum dados) {
        if (dados.nomeCompleto() != null) {
            super.setNomeCompleto(dados.nomeCompleto());
        }
        if (dados.email() != null) {
            super.setEmail(dados.email());
        }
        if (dados.cpf() != null) {
            this.cpf = dados.cpf();
        }
        if (dados.senha() != null) {
            super.setSenha(dados.senha());
        }
    }

}
