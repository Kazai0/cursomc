package br.com.cursomc.resources.excpetions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StanderError {
	
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}


	
	public List<FieldMessage> getErrors() {
		return errors;
	}


	public void addError(String fielName, String messagem) {
		errors.add(new FieldMessage(fielName, messagem));
	}



	

}
