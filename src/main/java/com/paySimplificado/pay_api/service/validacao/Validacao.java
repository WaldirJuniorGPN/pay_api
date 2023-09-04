package com.paySimplificado.pay_api.service.validacao;

import com.paySimplificado.pay_api.dto.request.DadosCadastroTransacao;

public interface Validacao {
    void validar (DadosCadastroTransacao dados);
}
