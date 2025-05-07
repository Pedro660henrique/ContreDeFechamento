package com.aro.FechamentoAro.entities;

import java.io.Serializable;
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
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FechamentoMotoboy implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
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
    @ToString.Exclude
    private List<EntradaMotoboy> entradas;
    
    @OneToMany(mappedBy = "fechamentoMotoboy", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<SaidaMotoboy> saidas;
    
    @OneToMany(mappedBy = "fechamentoMotoboy", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
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
