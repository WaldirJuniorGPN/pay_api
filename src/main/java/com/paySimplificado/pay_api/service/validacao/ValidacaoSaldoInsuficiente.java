package com.paySimplificado.pay_api.service.validacao;

import com.paySimplificado.pay_api.exception.ValidacaoException;
import com.paySimplificado.pay_api.model.Transacao;
import com.paySimplificado.pay_api.repository.UsuarioComumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoSaldoInsuficiente implements Validacao {

    @Autowired
    private UsuarioComumRepository usuarioComumRepository;

    @Override
    public void validar(Transacao transacao) {
        var usuarioOrigem = usuarioComumRepository.getReferenceById(transacao.getUsuarioOrigem().getId());
        var saldoDisponivel = usuarioOrigem.getSaldo();
        if (saldoDisponivel.compareTo(transacao.getValorDaTransacao()) < 0) {
            throw new ValidacaoException("Saldo insuficiente para proceguir com a transação");
        }
    }
}
