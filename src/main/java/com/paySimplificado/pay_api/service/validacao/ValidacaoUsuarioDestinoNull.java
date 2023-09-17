package com.paySimplificado.pay_api.service.validacao;

import com.paySimplificado.pay_api.exception.ValidacaoException;
import com.paySimplificado.pay_api.model.Transacao;
import com.paySimplificado.pay_api.repository.UsuarioComumRepository;
import com.paySimplificado.pay_api.repository.UsuarioLojistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoUsuarioDestinoNull implements Validacao {

    @Override
    public void validar(Transacao transascao) {
        var usuarioComumDestino = transascao.getUsuarioDestino();
        var usuarioLojistaDestino = transascao.getUsuarioDestino();
        if (usuarioComumDestino == null && usuarioLojistaDestino == null) {
            throw new ValidacaoException("Usuario de destino com ID " + transascao.getUsuarioDestino().getId() + " não foi encontrado nos registros do banco de dados");
        }
    }
}
