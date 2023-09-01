package com.paySimplificado.pay_api.model;

import com.paySimplificado.pay_api.dto.request.DadosAtualizacaoTransacao;
import com.paySimplificado.pay_api.dto.request.DadosCadastroTransacao;
import com.paySimplificado.pay_api.repository.TransacaoRepository;
import com.paySimplificado.pay_api.repository.UsuarioComunRepository;
import com.paySimplificado.pay_api.repository.UsuarioLogistaRepository;
import com.paySimplificado.pay_api.repository.UsuarioRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Entity(name = "Transacao")
@Table(name = "transacoes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Usuario usuarioOrigem;
    @ManyToOne
    private Usuario usuarioDestino;
    private BigDecimal valorDaOperacao;

    @Autowired
    private UsuarioRepository repository;

    public Transacao(DadosCadastroTransacao dados) {
        var usuarioOrigem = repository.getReferenceById(dados.idUsuarioOrigem());
        var usuarioDestino = repository.getReferenceById(dados.idUsuarioDestino());
        this.usuarioOrigem = usuarioOrigem;
        this.usuarioDestino = usuarioDestino;
        this.valorDaOperacao = dados.valorDaOperacao();
    }

    public void atualizarTransacao(DadosAtualizacaoTransacao dados) {
        if (dados.idUsuarioOrigem() != null) {
            this.usuarioOrigem = repository.getReferenceById(dados.idUsuarioOrigem());
        }
        if (dados.IdUsuarioDestino() != null) {
            this.usuarioDestino = repository.getReferenceById(dados.IdUsuarioDestino());
        }
        if (dados.valorDaOperacao() != null) {
            this.valorDaOperacao = dados.valorDaOperacao();
        }
    }
}
