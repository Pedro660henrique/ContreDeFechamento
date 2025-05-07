package com.aro.FechamentoAro.dto;

import com.aro.FechamentoAro.entities.Nivel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AtualizarUsuarioDTO {
	
	@NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    @Schema(description = "Nome completo do usuário", example = "João Silva")
    private String nome;
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Schema(description = "Email válido do usuário", example = "usuario@exemplo.com")
    private String email;
    
    @NotNull(message = "Nível é obrigatório")
    @Schema(description = "Nível de acesso do usuário")
    private Nivel nivel;

}
