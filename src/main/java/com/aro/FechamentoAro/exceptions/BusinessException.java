package com.aro.FechamentoAro.exceptions;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public static final String CAIXA_JA_ABERTO = "Já existe um caixa aberto para esta unidade. Feche o caixa atual antes de abrir um novo.";
    public static final String VALOR_INVALIDO = "O valor deve ser positivo. Valor informado: %s";
    public static final String OPERACAO_NAO_PERMITIDA = "Operação não permitida no estado atual do caixa: %s";
    public static final String USUARIO_NAO_AUTORIZADO = "Usuário não autorizado para esta ação. Permissão necessária: %s";

	public BusinessException(String message) {
        super(message);
    }
	
	public BusinessException(String messageTemplate, Object... params) {
	        super(String.format(messageTemplate, params));
	}
}
