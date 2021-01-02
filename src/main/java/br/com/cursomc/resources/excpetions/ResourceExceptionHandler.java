package br.com.cursomc.resources.excpetions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.cursomc.service.exceptions.DataIntegrityException;
import br.com.cursomc.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StanderError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
	
	StanderError err = new StanderError(HttpStatus.NOT_FOUND.value(),e.getMessage(),  System.currentTimeMillis());
	
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StanderError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
		StanderError err = new StanderError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	
}

}