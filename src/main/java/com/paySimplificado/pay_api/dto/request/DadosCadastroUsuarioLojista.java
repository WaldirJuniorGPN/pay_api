package com.paySimplificado.pay_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroUsuarioLojista(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter no mínimo 14 dígitos")
        String cnpj
) {
}
