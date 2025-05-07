package com.aro.FechamentoAro.dto;

import java.math.BigDecimal;

import com.aro.FechamentoAro.entities.MetodoPagamento;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EntradaDTO {
	
	@Schema(description = "Descrição da entrada", example = "Venda de produtos")
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;
    
    @Schema(description = "Valor da entrada (deve ser positivo)", example = "150.50")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    private BigDecimal valor;
    
    @Schema(description = "Método de pagamento utilizado")
    @NotNull(message = "Método de pagamento é obrigatório")
    private MetodoPagamento metodoPagamento;
    
    @Schema(description = "ID do fechamento de caixa relacionado")
    @NotNull(message = "Fechamento de caixa é obrigatório")
    private Long fechamentoCaixaId;
    
    @Schema(description = "ID do usuário que registrou a entrada")
    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

}
