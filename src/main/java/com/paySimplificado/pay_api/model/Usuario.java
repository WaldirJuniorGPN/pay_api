package com.paySimplificado.pay_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.math.BigDecimal;

@Entity(name = "Usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class Usuario {

    private String nome;
    @Column(unique = true)
    private String email;
    private BigDecimal saldo;
}
