package com.paySimplificado.pay_api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosAtualizacaoTransacao(
        @NotNull
        Long id,
        Long idUsuarioOrigem,
        Long IdUsuarioDestino,
        @Min(0)
        BigDecimal valorDaOperacao

) {
}
