package br.com.felipeduarte.gerenciadordeestudo.service.exceptions;

public class IllegalParameterException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IllegalParameterException(String message) {
		super(message);
	}

}
