package com.paySimplificado.pay_api.service.validacao;

import com.paySimplificado.pay_api.dto.request.DadosCadastroTransacao;
import com.paySimplificado.pay_api.exception.ValidacaoException;
import com.paySimplificado.pay_api.model.Transacao;
import com.paySimplificado.pay_api.repository.UsuarioComumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoUsuarioOrigemNull implements Validacao {

    @Override
    public void validar(Transacao transacao) {
        var usuarioOrigem = transacao.getUsuarioOrigem();
        if (usuarioOrigem == null) {
            throw new ValidacaoException("Usuario de origem com ID " + transacao.getUsuarioOrigem().getId() + " não foi encontrado nos registros do banco de dados");
        }
    }
}
