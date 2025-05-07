package com.aro.FechamentoAro.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SaidaMotoboy implements Serializable{
	private static final long serialVersionUID = 1L;
	
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private BigDecimal valor;
    private LocalDateTime dataHora;
    
    @ManyToOne
    private Motoboy motoboy;
    
    @ManyToOne
    private FechamentoMotoboy fechamentoMotoboy;
}
