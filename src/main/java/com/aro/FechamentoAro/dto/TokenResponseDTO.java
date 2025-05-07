package com.aro.FechamentoAro.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class TokenResponseDTO {
	@Schema(description = "Token JWT para autenticação")
    private final String token;

    public TokenResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}   