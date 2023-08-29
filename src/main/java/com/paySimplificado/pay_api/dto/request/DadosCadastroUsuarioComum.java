package com.paySimplificado.pay_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroUsuarioComum(
        @NotBlank
        String nomeCompleto,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @NotBlank
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos.")
        String cpf

) {
}
