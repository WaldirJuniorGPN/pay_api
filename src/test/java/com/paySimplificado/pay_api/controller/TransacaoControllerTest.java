package com.paySimplificado.pay_api.controller;

import com.paySimplificado.pay_api.model.Transacao;
import com.paySimplificado.pay_api.model.Usuario;
import com.paySimplificado.pay_api.model.UsuarioComum;
import com.paySimplificado.pay_api.model.UsuarioLojista;
import com.paySimplificado.pay_api.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TransacaoControllerTest {

    @Autowired
    private TransacaoRepository transacaoRepository;

    private Usuario usuarioOrigem;
    private Usuario usuarioDestino;

    @BeforeEach
    void setUp() {
        this.usuarioOrigem = new UsuarioComum();
        this.usuarioOrigem.setSaldo(new BigDecimal("1000"));
        this.usuarioDestino = new UsuarioLojista();
    }

    @Test
    void cadastrarTransacaoNoBancoDeDados() {

        var transacao = new Transacao(this.usuarioOrigem, this.usuarioDestino, new BigDecimal("500"));

        transacaoRepository.save(transacao);

        var transacaoSalva = transacaoRepository.findById(1L).orElse(null);
        assertNotNull(transacaoSalva);
        assertEquals(transacao, transacaoSalva);
    }

}