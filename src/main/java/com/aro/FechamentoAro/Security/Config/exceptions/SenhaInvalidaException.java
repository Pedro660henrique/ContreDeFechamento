package com.aro.FechamentoAro.Security.Config.exceptions;

public class SenhaInvalidaException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public SenhaInvalidaException(String message) {
        super(message);
    }
}
