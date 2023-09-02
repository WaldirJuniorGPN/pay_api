package com.paySimplificado.pay_api.service.validadores;

import com.paySimplificado.pay_api.dto.request.DadosCadastroTransacao;
import org.springframework.stereotype.Component;

@Component
public interface Validacao {

    void validar(DadosCadastroTransacao dados);
}
