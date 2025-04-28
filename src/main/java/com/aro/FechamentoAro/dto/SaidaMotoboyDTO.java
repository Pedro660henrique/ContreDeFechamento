package com.aro.FechamentoAro.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SaidaMotoboyDTO {
    private String tipo;
    private BigDecimal valor;
    private Long motoboyId;
}
