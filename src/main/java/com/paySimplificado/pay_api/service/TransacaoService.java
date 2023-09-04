package com.paySimplificado.pay_api.service;

import com.paySimplificado.pay_api.dto.request.DadosCadastroTransacao;
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

    public void efetuarTransacao(DadosCadastroTransacao dados, RecuperaUsuario usuario) {
        validadores.forEach(validacao -> validacao.validar(dados));
        var usuarioOrigem = usuario.getUsuarioOrigem();
        var usuarioDestino = usuario.getUsuarioDestino();
        saca(usuarioOrigem, dados.valorDaTransacao());
        deposita(usuarioDestino, dados.valorDaTransacao());
    }
}
