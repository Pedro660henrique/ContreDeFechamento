package com.aro.FechamentoAro.Security.Config;

import lombok.Data;

@Data
public class AuthRequest {
	
	private String email;
    private String senha;
}
