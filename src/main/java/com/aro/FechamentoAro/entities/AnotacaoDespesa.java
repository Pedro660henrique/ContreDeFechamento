package com.aro.FechamentoAro.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class AnotacaoDespesa implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 private String descricao;
	 
	 @NotNull(message = "Valor é obrigatório")
	 @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
	 private BigDecimal valor;

	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "fechamento_caixa_id", nullable = false)
	 private FechamentoCaixa fechamentoCaixa;
}
