package com.aro.FechamentoAro.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FechamentoCaixaDTO {
	@Schema(description = "ID da unidade onde o caixa será aberto")
    @NotNull(message = "Unidade é obrigatória")
    private Long unidadeId;
    
    @Schema(description = "Valor inicial do troco (deve ser positivo)", example = "100.00")
    @DecimalMin(value = "0.00", message = "Troco inicial deve ser positivo")
    private BigDecimal inicioTroco;
    
    @Schema(description = "ID do usuário que está abrindo o caixa")
    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioAberturaId;
}
