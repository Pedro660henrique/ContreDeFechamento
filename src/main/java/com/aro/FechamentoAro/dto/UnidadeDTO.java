package com.aro.FechamentoAro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnidadeDTO {
	
	@Schema(description = "ID da unidade (gerado automaticamente)", example = "1")
    private Long id;
    
    @NotBlank(message = "Nome da unidade é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    @Schema(description = "Nome da unidade", example = "Unidade Centro")
    private String nome;
    
    @NotBlank(message = "Endereço é obrigatório")
    @Size(max = 255, message = "Endereço deve ter no máximo 255 caracteres")
    @Schema(description = "Endereço completo da unidade", example = "Rua Principal, 123 - Centro")
    private String endereco;
    
    @Schema(description = "Indica se a unidade está ativa", example = "true")
    private boolean ativa;
}
