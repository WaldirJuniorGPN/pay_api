package com.paySimplificado.pay_api.service.validacao;

import com.paySimplificado.pay_api.dto.request.DadosCadastroTransacao;
import com.paySimplificado.pay_api.exception.ValidacaoException;
import com.paySimplificado.pay_api.repository.UsuarioComumRepository;
import com.paySimplificado.pay_api.repository.UsuarioLojistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoUsuarioDestinoNull implements Validacao {

    @Autowired
    private UsuarioComumRepository usuarioComumRepository;
    @Autowired
    private UsuarioLojistaRepository usuarioLojistaRepository;

    @Override
    public void validar(DadosCadastroTransacao dados) {
        var usuarioComumDestino = this.usuarioComumRepository.getReferenceById(dados.idUsuarioDestino());
        var usuarioLojistaDestino = this.usuarioLojistaRepository.getReferenceById(dados.idUsuarioDestino());
        if (usuarioComumDestino == null && usuarioLojistaDestino == null) {
            throw new ValidacaoException("Usuario de destino com ID " + dados.idUsuarioOrigem() + " não foi encontrado nos registros do banco de dados");
        }
    }
}
