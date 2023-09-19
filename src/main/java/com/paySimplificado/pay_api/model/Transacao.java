package com.paySimplificado.pay_api.model;

import com.paySimplificado.pay_api.dto.request.DadosCadastroTransacao;
import com.paySimplificado.pay_api.repository.UsuarioComumRepository;
import com.paySimplificado.pay_api.repository.UsuarioLojistaRepository;
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
    private BigDecimal valorDaTransacao;

    public Transacao(Usuario usuarioOrigem, Usuario usuarioDestino, BigDecimal valorDaTransacao) {
        this.usuarioOrigem = usuarioOrigem;
        this.usuarioDestino = usuarioDestino;
        this.valorDaTransacao = valorDaTransacao;
    }

}
