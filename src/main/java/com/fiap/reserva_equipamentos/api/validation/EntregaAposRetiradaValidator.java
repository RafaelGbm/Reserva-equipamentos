package com.fiap.reserva_equipamentos.api.validation;

import com.fiap.reserva_equipamentos.api.dto.CriarReservaDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EntregaAposRetiradaValidator implements ConstraintValidator<EntregaAposRetirada, CriarReservaDTO> {
    @Override
    public boolean isValid(CriarReservaDTO dto, ConstraintValidatorContext ctx){
        if (dto.getHorarioRetirada() == null || dto.getHorarioEntrega() == null) return true;
        return dto.getHorarioEntrega().isAfter(dto.getHorarioRetirada());
    }
}