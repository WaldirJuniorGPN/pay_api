package com.paySimplificado.pay_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Usuario")
@Table(name = "usuarios")
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario {
    @Id
    @TableGenerator(name = "usuario_gen", table = "sequence", pkColumnName = "name", valueColumnName = "next_value", pkColumnValue = "usuario_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "usuario_gen")
    @Setter(AccessLevel.NONE)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private BigDecimal saldo;
}
