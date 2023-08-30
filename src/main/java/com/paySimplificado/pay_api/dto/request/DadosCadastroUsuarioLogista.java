package com.paySimplificado.pay_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroUsuarioLogista(
        @NotBlank
        String nomeCompleto,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @NotBlank
        @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter exatamente 14 dígitos numéricos.")
        String cnpj
) {
}
