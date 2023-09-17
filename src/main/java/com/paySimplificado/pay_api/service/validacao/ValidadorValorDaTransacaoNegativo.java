package com.paySimplificado.pay_api.service.validacao;

import com.paySimplificado.pay_api.model.Transacao;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ValidadorValorDaTransacaoNegativo implements Validacao {

    @Override
    public void validar(Transacao transacao) {
        var valorDaTransacao = transacao.getValorDaTransacao();
        if(valorDaTransacao.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("O valor da transação não pode ser negativo");
        }
    }
}
