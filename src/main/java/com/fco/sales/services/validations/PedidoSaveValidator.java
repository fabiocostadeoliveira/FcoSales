package com.fco.sales.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.fco.sales.domain.Pedido;
import com.fco.sales.dtos.PedidoDTO;
import com.fco.sales.resouces.exceptions.FieldMessage;
import com.fco.sales.services.PedidoService;
import com.fco.sales.utils.RequestUtil;

public class PedidoSaveValidator implements ConstraintValidator<PedidoSave, PedidoDTO>{

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	PedidoService pedidoService;
	
	@Override
	public boolean isValid(PedidoDTO value, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		Pedido pedidoAux = null;
		
		Integer pedidoId = RequestUtil.getIntegerURLParamFromRequest(request, "id", null);
		
		if (pedidoId != null) {

			pedidoAux = pedidoService.findWithoutValidation(pedidoId);
		}
		
		if (pedidoAux != null) {
			
			list.addAll(getValidationsErrosUpdateProject(value, pedidoAux));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
			
		return list.isEmpty();
	}
	
	private List<FieldMessage> getValidationsErrosUpdateProject(PedidoDTO value, Pedido oldValue){
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(oldValue.isFinalizado()) {
			list.add(new FieldMessage("finalizado","O pedido ja foi finalizado."));
		}
		
		
		return list;
	}
	
	
	@SuppressWarnings("unused")
	private List<FieldMessage> getValidationsErrosNewProject(PedidoDTO value){
		
		// TODO - nao implementado
		List<FieldMessage> list = new ArrayList<>();
			
		return list;
	}

}
