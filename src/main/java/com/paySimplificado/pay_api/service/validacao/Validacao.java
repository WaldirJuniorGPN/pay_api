package com.paySimplificado.pay_api.service.validacao;

import com.paySimplificado.pay_api.dto.request.DadosCadastroTransacao;
import com.paySimplificado.pay_api.model.Transacao;

public interface Validacao {
    void validar (Transacao transacao);
}
