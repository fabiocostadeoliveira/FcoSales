package com.fco.sales.services.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = PedidoSaveValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PedidoSave {
	
	String message() default "Erro de validação ao alterar projeto";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
