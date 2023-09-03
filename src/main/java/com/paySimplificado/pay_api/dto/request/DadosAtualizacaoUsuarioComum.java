package com.paySimplificado.pay_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoUsuarioComum(
        @NotNull
        Long id,
        String nome,
        @Email
        String email,
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter no mínimo 11 dígitos")
        String cpf
) {
}
