package com.paySimplificado.pay_api.service;

import com.paySimplificado.pay_api.model.Usuario;
import com.paySimplificado.pay_api.model.UsuarioComum;
import com.paySimplificado.pay_api.model.UsuarioLojista;
import com.paySimplificado.pay_api.repository.UsuarioComumRepository;
import com.paySimplificado.pay_api.repository.UsuarioLojistaRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class RecuperaUsuario {

    private UsuarioComumRepository usuarioComumRepository;
    private UsuarioLojistaRepository usuarioLojistaRepository;
    private Usuario usuarioOrigem;
    private Usuario usuarioDestino;
    private List<Usuario> listaUsuarios = new ArrayList<>();

    @Autowired
    public RecuperaUsuario(UsuarioComumRepository usuarioComumRepository, UsuarioLojistaRepository usuarioLojistaRepository){
        this.usuarioComumRepository = usuarioComumRepository;
        this.usuarioLojistaRepository = usuarioLojistaRepository;
    }

    public List<Usuario> recuperarUsuarios(Long idUsuarioOrigem, Long idUsuarioDestino) {
        var usuarioOrigem = this.usuarioComumRepository.getReferenceById(idUsuarioOrigem);
        this.listaUsuarios.add(usuarioOrigem);
        if (this.usuarioComumRepository.getReferenceById(idUsuarioDestino) != null) {
            var usuarioDestino = this.usuarioComumRepository.getReferenceById(idUsuarioDestino);
            this.listaUsuarios.add(usuarioDestino);
        } else {
             var usuarioDestino = this.usuarioLojistaRepository.getReferenceById(idUsuarioDestino);
             this.listaUsuarios.add(usuarioDestino);
        }
        return this.listaUsuarios;
    }

//    public Usuario getUsuarioOrigem() {
//        return this.usuarioOrigem;
//    }
//
//    public Usuario getUsuarioDestino() {
//        return this.usuarioDestino;
//    }
}
