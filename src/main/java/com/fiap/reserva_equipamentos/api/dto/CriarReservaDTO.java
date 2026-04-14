package com.fiap.reserva_equipamentos.api.dto;

import com.fiap.reserva_equipamentos.api.validation.EntregaAposRetirada;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@EntregaAposRetirada
@Data
public class CriarReservaDTO {

    @NotNull(message = "Equipamento é obrigatório")
    private Long idEquipamento;

    @NotNull(message = "Professor é obrigatório")
    private Long idProfessor;

    @NotNull(message = "Sala é obrigatório")
    private String sala;

    @NotNull @FutureOrPresent(message = "Retirada deve ser no presente ou futuro")
    private LocalDateTime horarioRetirada;

    @NotNull @Future(message = "Entrega deve ser no futuro")
    private LocalDateTime horarioEntrega;
}