package com.paySimplificado.pay_api.service;

import com.paySimplificado.pay_api.dto.request.DadosCadastroTransacao;
import com.paySimplificado.pay_api.model.Transacao;
import com.paySimplificado.pay_api.model.UsuarioComum;
import com.paySimplificado.pay_api.model.UsuarioLojista;
import com.paySimplificado.pay_api.repository.UsuarioComumRepository;
import com.paySimplificado.pay_api.repository.UsuarioLojistaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TransacaoServiceTest {

    @Autowired
    private TransacaoService transacaoService;
    @Autowired
    private UsuarioComumRepository usuarioComumRepository;
    @Autowired
    private UsuarioLojistaRepository usuarioLojistaRepository;
    @Autowired
    private RecuperaUsuario recuperaUsuario;
    private DadosCadastroTransacao dtoTransacao;
    private UsuarioComum usuarioComumOrigem;
    private UsuarioLojista usuarioLojistaDestino;
    private Transacao transacao;

    @BeforeEach
    void setUp() {
        this.usuarioComumOrigem = new UsuarioComum();
        this.usuarioComumOrigem.setSaldo(new BigDecimal("1000"));
        this.usuarioLojistaDestino = new UsuarioLojista();
        this.usuarioLojistaDestino.setSaldo(BigDecimal.ZERO);

        this.usuarioComumRepository.save(this.usuarioComumOrigem);
        this.usuarioLojistaRepository.save(this.usuarioLojistaDestino);

        this.dtoTransacao = new DadosCadastroTransacao(1L, 2L, new BigDecimal("500"));
        this.recuperaUsuario.recuperarUsuarios(dtoTransacao.idUsuarioOrigem(), dtoTransacao.idUsuarioDestino());
        this.transacao = new Transacao(this.recuperaUsuario.getUsuarioOrigem(), this.usuarioLojistaDestino, dtoTransacao.valorDaTransacao());
    }

    @Test
    @Transactional
    void verificaSaqueNaContaDeOrigem() {

        this.transacaoService.efetuarTransacao(this.transacao);
        assertEquals(this.usuarioComumOrigem.getSaldo(), new BigDecimal("500"));
    }
}