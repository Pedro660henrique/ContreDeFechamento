package com.aro.FechamentoAro.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class FechamentoMotoboy {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private LocalDate data;
    private BigDecimal valorRecebido;
    private BigDecimal despesas;
    private BigDecimal retiradas;
    private BigDecimal saldoFinal;
    
    @ManyToOne
    private Motoboy motoboy;
    
    @OneToMany(mappedBy = "fechamentoMotoboy", cascade = CascadeType.ALL)
    private List<EntradaMotoboy> entradas;
    
    @OneToMany(mappedBy = "fechamentoMotoboy", cascade = CascadeType.ALL)
    private List<SaidaMotoboy> saidas;
}
