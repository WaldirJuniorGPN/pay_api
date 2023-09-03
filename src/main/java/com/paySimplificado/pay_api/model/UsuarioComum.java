package com.paySimplificado.pay_api.model;

import com.paySimplificado.pay_api.dto.request.DadosAtualizacaoUsuarioComum;
import com.paySimplificado.pay_api.dto.request.DadosCadastroUsuarioComum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "UsuarioComum")
@Table(name = "usuarios-comuns")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UsuarioComum extends Usuario {

    @Column(unique = true)
    private String cpf;

    public UsuarioComum(DadosCadastroUsuarioComum dados) {
        super(null, dados.nome(), dados.email(), BigDecimal.ZERO);
        this.cpf = dados.cpf();
    }

    public void atualizarDados(DadosAtualizacaoUsuarioComum dados) {
        if (dados.nome() != null) {
            super.setNome(dados.nome());
        }
        if (dados.email() != null) {
            super.setEmail(dados.email());
        }
        if (dados.cpf() != null) {
            this.cpf = dados.cpf();
        }
    }
}
