package com.fiap.reserva_equipamentos.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EntregaAposRetiradaValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EntregaAposRetirada {
    String message() default "Horário de entrega deve ser após o de retirada";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}