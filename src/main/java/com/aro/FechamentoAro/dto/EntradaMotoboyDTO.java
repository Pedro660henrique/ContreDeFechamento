package com.aro.FechamentoAro.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class EntradaMotoboyDTO {
    private String descricao;
    private BigDecimal valor;
    private Long motoboyId;
}
