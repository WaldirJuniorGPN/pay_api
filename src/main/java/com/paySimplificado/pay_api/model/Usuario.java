package com.paySimplificado.pay_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo-usuario", discriminatorType = DiscriminatorType.STRING)
@EqualsAndHashCode(of = "id")
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String nomeCompleto;
    @Column(unique = true)
    private String email;
    private String senha;
    private BigDecimal carteira;

}
