package com.paySimplificado.pay_api.dto.reponse;

import com.paySimplificado.pay_api.model.Transacao;

import java.math.BigDecimal;

public record DadosListagemTransacao(Long id, Long idUsuarioOrigem, Long idUsuarioDestino,
                                     BigDecimal valorDaTransacao) {
    public DadosListagemTransacao(Transacao transacao) {
        this(transacao.getId(), transacao.getUsuarioOrigem().getId(), transacao.getUsuarioDestino().getId(), transacao.getValorDaTransacao());
    }
}
