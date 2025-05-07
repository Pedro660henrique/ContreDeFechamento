package com.aro.FechamentoAro.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaidaMotoboyDTO {
	@Schema(description = "Tipo da saída", example = "COMBUSTIVEL")
    @NotBlank(message = "Tipo é obrigatório")
    private String tipo;
    
    @Schema(description = "Valor da saída (deve ser positivo)", example = "30.00")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    private BigDecimal valor;
    
    @Schema(description = "ID do motoboy relacionado")
    @NotNull(message = "Motoboy é obrigatório")
    private Long motoboyId;
}
