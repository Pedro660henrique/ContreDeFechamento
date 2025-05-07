package com.aro.FechamentoAro.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaidaDTO {

	@Schema(description = "Tipo da saída", example = "COMPRA_MATERIAL")
    @NotBlank(message = "Tipo é obrigatório")
    private String tipo;
    
    @Schema(description = "Valor da saída (deve ser positivo)", example = "150.00")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    private BigDecimal valor;
    
    @Schema(description = "ID do fechamento de caixa relacionado")
    @NotNull(message = "Fechamento de caixa é obrigatório")
    private Long fechamentoCaixaId;
    
    @Schema(description = "ID do usuário que registrou a saída")
    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;
}
