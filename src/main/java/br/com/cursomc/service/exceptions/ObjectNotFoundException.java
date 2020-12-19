package br.com.cursomc.service.exceptions;

import javassist.SerialVersionUID;

public class ObjectNotFoundException extends RuntimeException{
	
	private static final long SerialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}

	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
