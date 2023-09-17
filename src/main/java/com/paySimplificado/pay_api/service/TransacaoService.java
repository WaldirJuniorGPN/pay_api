package com.paySimplificado.pay_api.service;

import com.paySimplificado.pay_api.dto.request.DadosCadastroTransacao;
import com.paySimplificado.pay_api.model.Transacao;
import com.paySimplificado.pay_api.model.Usuario;
import com.paySimplificado.pay_api.service.validacao.Validacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private List<Validacao> validadores;
    private void saca(Usuario usuarioOrigem, BigDecimal valorDaTransacao) {
        var saldoAtual = usuarioOrigem.getSaldo();
        usuarioOrigem.setSaldo(saldoAtual.subtract(valorDaTransacao));
    }

    private void deposita(Usuario usuarioDestino, BigDecimal valorDaTransacao) {
        var saldoAtual = usuarioDestino.getSaldo();
        usuarioDestino.setSaldo(saldoAtual.add(valorDaTransacao));
    }

    public void efetuarTransacao(Transacao transacao) {
        validadores.forEach(validacao -> validacao.validar(transacao));
        var usuarioOrigem = transacao.getUsuarioOrigem();
        var usuarioDestino = transacao.getUsuarioDestino();
        var valorDaTransacao = transacao.getValorDaTransacao();
        saca(usuarioOrigem, valorDaTransacao);
        deposita(usuarioDestino, valorDaTransacao);
    }
}
