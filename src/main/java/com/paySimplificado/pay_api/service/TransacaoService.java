package com.paySimplificado.pay_api.service;

import com.paySimplificado.pay_api.dto.request.DadosCadastroTransacao;
import com.paySimplificado.pay_api.model.Usuario;
import com.paySimplificado.pay_api.service.validadores.Validacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private List<Validacao> validadores;
    @Autowired
    private UsuarioRepository repository;

    private void saca(Usuario origem, BigDecimal valor) {
        var valorAtual = origem.getCarteira();
        origem.setCarteira(valorAtual.subtract(valor));
    }

    private void deposita(Usuario destino, BigDecimal valor) {
        var valorAtual = destino.getCarteira();
        destino.setCarteira(valorAtual.add(valor));
    }

    public void transfere(DadosCadastroTransacao dados) {
        validadores.forEach(validacao -> validacao.validar(dados));
        var usuarioOrigem = repository.getReferenceById(dados.idUsuarioOrigem());
        var usuarioDestino = repository.getReferenceById(dados.idUsuarioDestino());

        saca(usuarioOrigem, dados.valorDaOperacao());
        deposita(usuarioDestino, dados.valorDaOperacao());
    }

}
