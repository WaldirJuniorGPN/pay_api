package com.paySimplificado.pay_api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosCadastroTransacao(
        @NotNull
        Long idUsuarioOrigem,
        @NotNull
        Long idUsuarioDestino,
        @NotNull
        @Min(0)
        BigDecimal valorDaTransacao
) {
}
