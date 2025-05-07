package com.aro.FechamentoAro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CriarUnidadeDTO {
	
	@NotBlank(message = "Nome da unidade é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    @Schema(description = "Nome da unidade", example = "Unidade Centro")
    private String nome;
    
    @NotBlank(message = "Endereço é obrigatório")
    @Size(max = 255, message = "Endereço deve ter no máximo 255 caracteres")
    @Schema(description = "Endereço completo da unidade", example = "Rua Principal, 123 - Centro")
    private String endereco;

}
