package com.paySimplificado.pay_api.service.validacao;

import com.paySimplificado.pay_api.exception.ValidacaoException;
import com.paySimplificado.pay_api.model.Transacao;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoSaldoInsuficiente implements Validacao {

    @Override
    public void validar(Transacao transacao) {
        var usuarioOrigem = transacao.getUsuarioOrigem();
        var saldoDisponivel = usuarioOrigem.getSaldo();
        if (saldoDisponivel.compareTo(transacao.getValorDaTransacao()) < 0) {
            throw new ValidacaoException("Saldo insuficiente para proceguir com a transação");
        }
    }
}
