package com.paySimplificado.pay_api.service;

import com.paySimplificado.pay_api.dto.request.DadosCadastroTransacao;
import com.paySimplificado.pay_api.exception.ValidacaoException;
import com.paySimplificado.pay_api.model.Transacao;
import com.paySimplificado.pay_api.model.Usuario;
import com.paySimplificado.pay_api.model.UsuarioComum;
import com.paySimplificado.pay_api.model.UsuarioLojista;
import com.paySimplificado.pay_api.repository.UsuarioComumRepository;
import com.paySimplificado.pay_api.repository.UsuarioLojistaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class TransacaoServiceTest {

    private final static Integer USUARIO_ORIGEM_INDICE = 0;
    private final static Integer USUARIO_DESTINO_INDICE = 1;
    @Autowired
    private TransacaoService transacaoService;
    @Mock
    private UsuarioComumRepository usuarioComumRepository;
    @Mock
    private UsuarioLojistaRepository usuarioLojistaRepository;
    @Mock
    private RecuperaUsuario recuperaUsuario = new RecuperaUsuario(usuarioComumRepository, usuarioLojistaRepository);
    private DadosCadastroTransacao dtoTransacao;
    private UsuarioComum usuarioComumOrigem;
    private UsuarioLojista usuarioLojistaDestino;
    private Transacao transacao;
    private List<Usuario> listaDeUsuarios;

    @BeforeEach
    void setUp() {
        this.usuarioComumOrigem = new UsuarioComum();
        this.usuarioComumOrigem.setSaldo(new BigDecimal("1000"));
        this.usuarioLojistaDestino = new UsuarioLojista();
        this.usuarioLojistaDestino.setSaldo(BigDecimal.ZERO);

        this.listaDeUsuarios = List.of(this.usuarioComumOrigem, this.usuarioLojistaDestino);

        this.usuarioComumRepository.save(this.usuarioComumOrigem);
        this.usuarioLojistaRepository.save(this.usuarioLojistaDestino);

        this.dtoTransacao = new DadosCadastroTransacao(1L, 2L, new BigDecimal("500"));
        Mockito.when(recuperaUsuario.recuperarUsuarios(dtoTransacao.idUsuarioOrigem(), dtoTransacao.idUsuarioDestino())).thenReturn(this.listaDeUsuarios);

        this.recuperaUsuario.recuperarUsuarios(dtoTransacao.idUsuarioOrigem(), dtoTransacao.idUsuarioDestino());
        this.transacao = new Transacao(this.listaDeUsuarios.get(USUARIO_ORIGEM_INDICE), this.listaDeUsuarios.get(USUARIO_DESTINO_INDICE), this.dtoTransacao.valorDaTransacao());
    }

    @Test
    @Transactional
    void verificaSaqueNaContaDeOrigem() {

        Mockito.when(usuarioComumRepository.findById(dtoTransacao.idUsuarioOrigem())).thenReturn(Optional.ofNullable(usuarioComumOrigem));
        this.transacaoService.efetuarTransacao(this.transacao);
        var usuarioNoBanco = this.usuarioComumRepository.findById(dtoTransacao.idUsuarioOrigem()).orElse(null);
        assertEquals(this.usuarioComumOrigem.getSaldo(), usuarioNoBanco.getSaldo());
        assertEquals(this.usuarioComumOrigem.getSaldo(), new BigDecimal("500"));
    }

    @Test
    @Transactional
    void verificaDepositoNaContaDeDestino() {
        this.transacaoService.efetuarTransacao(this.transacao);
        Mockito.when(this.usuarioLojistaRepository.findById((dtoTransacao.idUsuarioDestino()))).thenReturn(Optional.ofNullable(this.usuarioLojistaDestino));
        var usuarioNoBanco = this.usuarioLojistaRepository.findById(dtoTransacao.idUsuarioDestino()).orElse(null);
        assertEquals(this.usuarioLojistaDestino.getSaldo(), usuarioNoBanco.getSaldo());
        assertEquals(this.usuarioLojistaDestino.getSaldo(), new BigDecimal("500"));
    }

    @Test
    @Transactional
    void verificaValidacaoSaldoInsuficiente() {
        var valorMuitoAltoDaTransacao = new BigDecimal("2000");
        var transacaoSaldoInsuciente = new Transacao(this.listaDeUsuarios.get(USUARIO_ORIGEM_INDICE), this.listaDeUsuarios.get(USUARIO_DESTINO_INDICE), valorMuitoAltoDaTransacao);
        var exception = assertThrows(ValidacaoException.class, () -> {
            this.transacaoService.efetuarTransacao(transacaoSaldoInsuciente);
        });
        assertEquals("Saldo insuficiente para proceguir com a transação", exception.getMessage());
    }

    @Test
    @Transactional
    void verificaValidacaoValorDaTransacaoNegativo() {
        var valorNegativoDaTransacao = new BigDecimal("-2000");
        var transacaoValorNegativo = new Transacao(this.listaDeUsuarios.get(USUARIO_ORIGEM_INDICE), this.listaDeUsuarios.get(USUARIO_DESTINO_INDICE), valorNegativoDaTransacao);

        var exception = assertThrows(IllegalArgumentException.class, () -> {
            this.transacaoService.efetuarTransacao(transacaoValorNegativo);
        });
        assertEquals("O valor da transação não pode ser negativo", exception.getMessage());
    }
}