package br.com.cursomc.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;



import br.com.cursomc.domain.Cliente;
import br.com.cursomc.domain.enums.TipoCliente;
import br.com.cursomc.dto.ClienteDTO;
import br.com.cursomc.dto.ClienteNewDTO;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.resources.excpetions.FieldMessage;
import br.com.cursomc.service.validation.utils.BR;
import ch.qos.logback.core.net.server.Client;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private HttpServletRequest request;
	
	
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
	
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();

	
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		
		if(aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		
		

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
