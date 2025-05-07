package com.aro.FechamentoAro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NovaSenhaDTO {
	@Schema(description = "Senha atual do usuário")
    @NotBlank(message = "Senha atual é obrigatória")
    private String senhaAtual;
    
    @Schema(description = "Nova senha (mínimo 8 caracteres)", example = "NovaSenha123!")
    @NotBlank(message = "Nova senha é obrigatória")
    @Size(min = 8, message = "Nova senha deve ter no mínimo 8 caracteres")
    private String novaSenha;
}
