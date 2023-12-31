package com.paySimplificado.pay_api.model;

import com.paySimplificado.pay_api.dto.request.DadosAtualizacaoUsuarioLogista;
import com.paySimplificado.pay_api.dto.request.DadosCadastroUsuarioLogista;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "UsuarioLogista")
@DiscriminatorValue("logista")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UsuarioLojista extends Usuario {

    @Column(unique = true)
    private String cnpj;

    public UsuarioLojista(DadosCadastroUsuarioLogista dados) {
        super.setNomeCompleto(dados.nomeCompleto());
        super.setEmail(dados.email());
        super.setSenha(dados.senha());
        this.cnpj = dados.cnpj();
    }

    public void atualizarDados(DadosAtualizacaoUsuarioLogista dados) {
        if (dados.nomeCompleto() != null) {
            super.setNomeCompleto(dados.nomeCompleto());
        }
        if (dados.email() != null) {
            super.setEmail(dados.email());
        }
        if (dados.senha() != null) {
            super.setEmail(dados.senha());
        }
        if (dados.cnpj() != null) {
            this.cnpj = dados.cnpj();
        }
    }
}
