package com.paySimplificado.pay_api.dto.response;

import com.paySimplificado.pay_api.model.Transacao;

import java.math.BigDecimal;

public record DadosDetalhamentoTransacao(Long id, Long idUsuarioOrigem, Long idUsuarioDestino,
                                         BigDecimal valorDaTransacao) {
    public DadosDetalhamentoTransacao(Transacao transacao) {
        this(transacao.getId(), transacao.getUsuarioOrigem().getId(), transacao.getUsuarioDestino().getId(), transacao.getValorDaOperacao());
    }
}
