package com.aro.FechamentoAro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FechamentoMotoboyDTO {
	@Schema(description = "ID do motoboy")
    @NotNull(message = "Motoboy é obrigatório")
    private Long motoboyId;
    
    @Schema(description = "Data do fechamento")
    @NotNull(message = "Data é obrigatória")
    private LocalDate data;
    
    @Schema(description = "Valor total recebido (deve ser positivo)", example = "500.00")
    @DecimalMin(value = "0.00", message = "Valor recebido deve ser positivo")
    private BigDecimal valorRecebido;
    
    @Schema(description = "Total de despesas (deve ser positivo)", example = "50.00")
    @DecimalMin(value = "0.00", message = "Despesas devem ser positivas")
    private BigDecimal despesas;
    
    @Schema(description = "Total de retiradas (deve ser positivo)", example = "100.00")
    @DecimalMin(value = "0.00", message = "Retiradas devem ser positivas")
    private BigDecimal retiradas;
    
    @Schema(description = "Lista de entregas realizadas")
    private List<EntregaDTO> entregas;
}
