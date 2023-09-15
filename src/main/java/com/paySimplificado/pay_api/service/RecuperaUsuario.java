package com.paySimplificado.pay_api.service;

import com.paySimplificado.pay_api.model.Usuario;
import com.paySimplificado.pay_api.repository.UsuarioComumRepository;
import com.paySimplificado.pay_api.repository.UsuarioLojistaRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class RecuperaUsuario {
    @Autowired
    private UsuarioComumRepository usuarioComumRepository;
    @Autowired
    private UsuarioLojistaRepository usuarioLojistaRepository;
    private Usuario usuarioOrigem;
    private Usuario usuarioDestino;

    public void recuperarUsuarios(Long idUsuarioOrigem, Long idUsuarioDestino) {
        var usuarioComumOrigem = usuarioComumRepository.getReferenceById(idUsuarioOrigem);
        var usuarioComumDestino = usuarioComumRepository.getReferenceById(idUsuarioDestino);
        var usuarioLojistaDestino = usuarioLojistaRepository.getReferenceById(idUsuarioDestino);

        this.usuarioOrigem = usuarioComumOrigem;

        if (usuarioComumDestino != null) {
            this.usuarioDestino = usuarioComumDestino;
        } else {
            this.usuarioDestino = usuarioLojistaDestino;
        }
    }

    public Usuario getUsuarioOrigem() {
        return usuarioOrigem;
    }

    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }
}
