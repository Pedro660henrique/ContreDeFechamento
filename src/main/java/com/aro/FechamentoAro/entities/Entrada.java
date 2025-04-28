package com.aro.FechamentoAro.entities;

import java.math.BigDecimal;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Entrada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalTime horario; 
	private String descricao;
	private BigDecimal valor;
	
	
	@ManyToOne
	private FechamentoCaixa fechamentoCaixa;
	
	@Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;
	
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}

