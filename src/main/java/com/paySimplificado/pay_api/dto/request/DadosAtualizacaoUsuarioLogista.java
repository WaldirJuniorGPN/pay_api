package com.paySimplificado.pay_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoUsuarioLogista(
        @NotNull
        Long id,
        String nomeCompleto,
        @Email
        String email,
        String senha,
        @Pattern(regexp = "\\d{14}", message = "O CNPJ precisa ter no mínimo 14 dígitos.")
        String cnpj
) {
}
