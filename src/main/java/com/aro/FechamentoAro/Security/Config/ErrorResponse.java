package com.aro.FechamentoAro.Security.Config;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String message;
    private long timestamp;

    // Construtor simplificado
    public ErrorResponse(String errorCode, String message, Date timestamp) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = timestamp.getTime();
    }

    // Construtor para erros simples
    public ErrorResponse(String message) {
        this("ERRO", message, new Date());
    }
}
