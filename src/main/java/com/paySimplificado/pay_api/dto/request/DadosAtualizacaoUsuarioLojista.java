package com.paySimplificado.pay_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoUsuarioLojista(
        @NotNull
        Long id,
        String nome,
        @Email
        String email,
        @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter no mínimo 14 dígitos")
        String cnpj
) {
}
