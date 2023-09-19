package com.paySimplificado.pay_api.service;

import com.paySimplificado.pay_api.dto.request.DadosCadastroUsuarioComum;
import com.paySimplificado.pay_api.dto.request.DadosCadastroUsuarioLojista;
import com.paySimplificado.pay_api.model.Usuario;
import com.paySimplificado.pay_api.model.UsuarioComum;
import com.paySimplificado.pay_api.model.UsuarioLojista;
import com.paySimplificado.pay_api.repository.UsuarioComumRepository;
import com.paySimplificado.pay_api.repository.UsuarioLojistaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class RecuperaUsuarioTest {

    private final static Integer USUARIO_ORIGEM_INDICE = 0;
    private final static Integer USUARIO_DESTINO_INDICE = 1;
    private UsuarioComum usuarioComumOrigem;
    private UsuarioComum usuarioComumDestino;
    private UsuarioLojista usuarioLojista;
    private List<Usuario> listaDeUsuarios;

    @Autowired
    private UsuarioComumRepository usuarioComumRepository;
    @Autowired
    private UsuarioLojistaRepository usuarioLojistaRepository;
    @Autowired
    private RecuperaUsuario recuperaUsuario;

    @BeforeEach
    void setUp() {
        this.usuarioComumOrigem = new UsuarioComum(new DadosCadastroUsuarioComum("Fulano", "email@email.com","99999999998"));
        this.usuarioComumDestino = new UsuarioComum(new DadosCadastroUsuarioComum("Ciclano", "ciclano@email.com", "99999999999"));
        this.usuarioLojista = new UsuarioLojista((new DadosCadastroUsuarioLojista("Beltrano", "beltrano@email.com", "9999999999999")));

        this.usuarioComumRepository.save(this.usuarioComumOrigem);
        this.usuarioComumRepository.save(this.usuarioComumDestino);
        this.usuarioLojistaRepository.save(this.usuarioLojista);
    }

    @Test
    void RecuperaUsuariosSalvosNoBancoDeDados() {
      this.listaDeUsuarios = this.recuperaUsuario.recuperarUsuarios(1L, 2L);

        assertNotNull(this.listaDeUsuarios);
        assertEquals(1L, this.listaDeUsuarios.get(USUARIO_ORIGEM_INDICE).getId());
        assertEquals(2L, this.listaDeUsuarios.get(USUARIO_DESTINO_INDICE).getId());
    }

}