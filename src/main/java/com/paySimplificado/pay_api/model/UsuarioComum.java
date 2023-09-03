package com.paySimplificado.pay_api.model;

import com.paySimplificado.pay_api.dto.request.DadosAtualizacaoUsuarioComum;
import com.paySimplificado.pay_api.dto.request.DadosCadastroUsuarioComum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "UsuarioComum")
@Table(name = "usuarios-comuns")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class UsuarioComum extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT AUTO_INCREMENT")
    private Long id;
    @Column(unique = true)
    private String cpf;

    public UsuarioComum(DadosCadastroUsuarioComum dados) {
        super(dados.nome(), dados.email(), BigDecimal.ZERO);
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
