package com.fco.sales.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fco.sales.dtos.UsuarioDTO;
import com.fco.sales.resouces.exceptions.FieldMessage;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioDTO>{

	@Override
	public boolean isValid(UsuarioDTO value, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		System.out.println("usuarioDto" +  value + "|");
		
		if(value.getSenha().equals("")) {
			list.add(new FieldMessage("senha","Senha nao pode ser vazia."));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
			
		return list.isEmpty();
	}
}
