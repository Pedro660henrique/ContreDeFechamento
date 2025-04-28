package com.aro.FechamentoAro.dto;

import java.math.BigDecimal;

import com.aro.FechamentoAro.entities.MetodoPagamento;

public class EntradaDTO {
	
    private String descricao;
    private BigDecimal valor;
    private MetodoPagamento metodoPagamento;
    private Long fechamentoCaixa;
    private Long usuarioId;

}
