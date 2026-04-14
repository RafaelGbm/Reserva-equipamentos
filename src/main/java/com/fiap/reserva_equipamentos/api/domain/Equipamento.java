package com.fiap.reserva_equipamentos.api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Equipamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Boolean ativo = true;
}