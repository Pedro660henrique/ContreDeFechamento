package com.aro.FechamentoAro.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntregaDTO {
	
	@Schema(description = "Número/identificador único da entrega", example = "ENT20230001")
    @NotBlank(message = "Número da entrega é obrigatório")
    @Size(min = 5, max = 50, message = "Número da entrega deve ter entre 5 e 50 caracteres")
    private String numeroEntrega;
    
    @Schema(description = "Valor da entrega (deve ser positivo)", example = "15.50")
    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor mínimo é 0.01")
    private BigDecimal valor;
    
    @Schema(description = "ID do motoboy responsável pela entrega")
    @NotNull(message = "Motoboy é obrigatório")
    private Long motoboyId;
    
    @Schema(description = "ID do fechamento relacionado")
    @NotNull(message = "Fechamento é obrigatório")
    private Long fechamentoMotoboyId;

}
