package com.aro.FechamentoAro.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
    
    @ManyToOne
    @JoinColumn(name = "unidade_id")
    private Unidade unidade;
    
    @OneToMany(mappedBy = "fechamentoMotoboy", cascade = CascadeType.ALL)
    private List<EntradaMotoboy> entradas;
    
    @OneToMany(mappedBy = "fechamentoMotoboy", cascade = CascadeType.ALL)
    private List<SaidaMotoboy> saidas;
    
    @OneToMany(mappedBy = "fechamentoMotoboy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Entregas> entregas;
   
    
    public BigDecimal calcularTotalEntregas() {
	    return entregas.stream()
	        .map(Entregas::getValor)
	        .reduce(BigDecimal.ZERO, BigDecimal::add);
	}
    
    @PrePersist
    @PreUpdate
    private void calcularSaldo() {
        this.valorRecebido = calcularTotalEntregas();
        this.saldoFinal = valorRecebido.subtract(despesas).subtract(retiradas);
    }
}
