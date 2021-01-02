package br.com.cursomc.service.exceptions;

import javassist.SerialVersionUID;

public class DataIntegrityException extends RuntimeException{
	
	private static final long SerialVersionUID = 1L;
	
	public DataIntegrityException(String msg) {
		super(msg);
	}

	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
