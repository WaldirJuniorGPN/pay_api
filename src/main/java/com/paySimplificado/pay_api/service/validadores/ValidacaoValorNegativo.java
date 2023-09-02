package com.paySimplificado.pay_api.service.validadores;

import com.paySimplificado.pay_api.dto.request.DadosCadastroTransacao;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ValidacaoValorNegativo implements Validacao {

    @Override
    public void validar(DadosCadastroTransacao dados) {
        if (dados.valorDaOperacao().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor da transação não pode ser negativo");
        }
    }
}
