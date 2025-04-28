package com.aro.FechamentoAro.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class AnotacaoDespesa {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 private String descricao;
	 private BigDecimal valor;

	 @ManyToOne
	 private FechamentoCaixa fechamentoCaixa;
}
