package com.paySimplificado.pay_api.model;

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
public class UsuarioLogista extends Usuario {

    @Column(unique = true)
    private String cnpj;

    public UsuarioLogista(DadosCadastroUsuarioLogista dados) {
        super.setNomeCompleto(dados.nomeCompleto());
        super.setEmail(dados.email());
        super.setSenha(dados.senha());
        this.cnpj = dados.cnpj();
    }
}
