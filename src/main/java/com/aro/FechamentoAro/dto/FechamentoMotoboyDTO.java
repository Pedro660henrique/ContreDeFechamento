package com.aro.FechamentoAro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class FechamentoMotoboyDTO {
    private Long motoboyId;
    private LocalDate data;
    private BigDecimal valorRecebido;
    private BigDecimal despesas;
    private BigDecimal retiradas;
}
