package com.aro.FechamentoAro.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AutentDTO {
	
	@Schema(description = "Email válido do usuário", example = "usuario@exemplo.com")
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;
    
    @Schema(description = "Senha do usuário (mínimo 8 caracteres)", example = "SenhaSegura123!")
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    private String senha;
}
