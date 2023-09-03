package com.paySimplificado.pay_api.model;

import com.paySimplificado.pay_api.dto.request.DadosAtualizacaoUsuarioLojista;
import com.paySimplificado.pay_api.dto.request.DadosCadastroUsuarioLojista;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "UsuarioLogista")
@Table(name = "usuarios-logistas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UsuarioLojista extends Usuario {

    @Column(unique = true)
    private String cnpj;

    public UsuarioLojista(DadosCadastroUsuarioLojista dados) {
        super(null, dados.nome(), dados.email(), BigDecimal.ZERO);
        this.cnpj = dados.cnpj();
    }

    public void atualizarDados(DadosAtualizacaoUsuarioLojista dados) {
        if (dados.nome() != null) {
            super.setNome(dados.nome());
        }
        if (dados.email() != null) {
            super.setEmail(dados.email());
        }
        if (dados.cnpj() != null) {
            this.cnpj = dados.cnpj();
        }
    }
}
