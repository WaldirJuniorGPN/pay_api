package com.paySimplificado.pay_api.dto.reponse;

import com.paySimplificado.pay_api.model.Transacao;

import java.math.BigDecimal;

public record DadosDetalhamentoTransacao(Long id, Long idUsuarioOrigem, Long idUsuarioDestino,
                                         BigDecimal valorDaTransacao) {
    public DadosDetalhamentoTransacao(Transacao transacao) {
        this(transacao.getId(), transacao.getUsuarioOrigem().getId(), transacao.getUsuarioOrigem().getId(), transacao.getValorDaTransacao());
    }
}
