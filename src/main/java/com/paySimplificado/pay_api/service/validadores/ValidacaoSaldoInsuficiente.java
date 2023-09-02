package com.paySimplificado.pay_api.service.validadores;

import com.paySimplificado.pay_api.dto.request.DadosCadastroTransacao;
import com.paySimplificado.pay_api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoSaldoInsuficiente implements Validacao {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validar(DadosCadastroTransacao dados) {
        var usuarioOrigem = repository.getReferenceById(dados.idUsuarioOrigem());
        if (usuarioOrigem.getCarteira().compareTo(dados.valorDaOperacao()) < 0) {
            throw new ValidacaoException("Saldo insuficiente para concluir a operação");
        }
    }
}
