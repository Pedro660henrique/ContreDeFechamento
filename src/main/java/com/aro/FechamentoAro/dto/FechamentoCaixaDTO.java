package com.aro.FechamentoAro.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FechamentoCaixaDTO {
	private Long unidadeId;
    private BigDecimal inicioTroco;
    private Long usuarioAberturaId;
}
