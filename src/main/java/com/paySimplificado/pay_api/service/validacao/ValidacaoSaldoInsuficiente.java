package com.paySimplificado.pay_api.service.validacao;

import com.paySimplificado.pay_api.dto.request.DadosCadastroTransacao;
import com.paySimplificado.pay_api.exception.ValidacaoException;
import com.paySimplificado.pay_api.repository.UsuarioComumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoSaldoInsuficiente implements Validacao {

    @Autowired
    private UsuarioComumRepository usuarioComumRepository;

    @Override
    public void validar(DadosCadastroTransacao dados) {
        var usuarioOrigem = usuarioComumRepository.getReferenceById(dados.idUsuarioOrigem());
        var saldoDisponivel = usuarioOrigem.getSaldo();
        if (saldoDisponivel.compareTo(dados.valorDaTransacao()) < 0) {
            throw new ValidacaoException("Saldo insuficiente para proceguir com a transação");
        }
    }
}
