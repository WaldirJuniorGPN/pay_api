package com.paySimplificado.pay_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroUsuarioComum(
        @NotNull
        Long id,
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotNull
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos")
        String cpf
) {}
