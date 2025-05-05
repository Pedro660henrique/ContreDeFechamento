package com.aro.FechamentoAro.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Entity
@Data
public class Entregas {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column(unique = true)
	private String numeroEntrega;
	
	@DecimalMin(value = "0.01", message = "Valor mínimo é 0.01")
	private BigDecimal valor;
	
	@ManyToOne
    @JoinColumn(name = "fechamento_id")
    private FechamentoMotoboy fechamentoMotoboy;
	
	@ManyToOne
	private Motoboy motoboy;
	
}
