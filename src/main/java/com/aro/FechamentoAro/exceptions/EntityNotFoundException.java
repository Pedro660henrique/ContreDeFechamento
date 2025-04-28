package com.aro.FechamentoAro.exceptions;

public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public static final String UNIDADE_NAO_ENCONTRADA = "Unidade não encontrada com ID: %s";
    public static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado com email: %s";
    public static final String CAIXA_NAO_ENCONTRADO = "Caixa não encontrado com ID: %s";
    public static final String MOTOBOY_NAO_ENCONTRADO = "Motoboy não encontrado com ID: %s";

	public EntityNotFoundException(String message) {
        super(message);
    }
	
	public EntityNotFoundException(String messageTemplate, Object... params) {
        super(String.format(messageTemplate, params));
    }
}