package com.paySimplificado.pay_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "UsuarioLogista")
@DiscriminatorValue("logista")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UsuarioLogista extends Usuario {

    @Column(unique = true)
    private String cnpj;
}
