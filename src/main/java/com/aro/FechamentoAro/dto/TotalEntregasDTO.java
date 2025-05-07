package com.aro.FechamentoAro.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TotalEntregasDTO {
	
	@Schema(description = "ID do fechamento relacionado", example = "1")
    private Long fechamentoMotoboyId;
    
    @Schema(description = "Total das entregas", example = "150.75")
    private BigDecimal total;
    
    @Schema(description = "Quantidade de entregas", example = "10")
    private int quantidade;

}
