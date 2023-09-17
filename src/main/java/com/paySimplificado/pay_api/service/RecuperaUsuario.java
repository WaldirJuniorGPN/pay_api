package com.paySimplificado.pay_api.service;

import com.paySimplificado.pay_api.model.Usuario;
import com.paySimplificado.pay_api.model.UsuarioComum;
import com.paySimplificado.pay_api.model.UsuarioLojista;
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
        this.usuarioOrigem = this.usuarioComumRepository.getReferenceById(idUsuarioOrigem);

        if (this.usuarioComumRepository.getReferenceById(idUsuarioDestino) != null) {
            this.usuarioDestino = this.usuarioComumRepository.getReferenceById(idUsuarioDestino);
        } else {
             this.usuarioDestino = this.usuarioLojistaRepository.getReferenceById(idUsuarioDestino);
        }
    }

    public Usuario getUsuarioOrigem() {
        return this.usuarioOrigem;
    }

    public Usuario getUsuarioDestino() {
        return this.usuarioDestino;
    }
}
