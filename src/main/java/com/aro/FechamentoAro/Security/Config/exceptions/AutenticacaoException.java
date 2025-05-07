package com.aro.FechamentoAro.Security.Config.exceptions;

public class AutenticacaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AutenticacaoException(String message) {
        super(message);
    }
}
