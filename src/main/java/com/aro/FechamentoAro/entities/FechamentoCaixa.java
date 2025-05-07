
package com.aro.FechamentoAro.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FechamentoCaixa implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private LocalDate data;
	private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;
    private BigDecimal inicioTroco;
    private BigDecimal terminoTroco;
    private BigDecimal totalFechamento;

	
	@ManyToOne
	private Unidade unidade;
	
    // Relacionamentos com as movimentações detalhadas
	@OneToMany(mappedBy = "fechamentoCaixa", cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<Entrada> entradas;
	
	@OneToMany(mappedBy = "fechamentoCaixa", cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<Saida> saidas;
	
	@OneToMany(mappedBy = "fechamentoCaixa", cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<AnotacaoDespesa> anotacoes;
	
	@ManyToOne
    @JoinColumn(name = "usuario_abertura_id")
    private Usuario usuarioAbertura;

    @ManyToOne
    @JoinColumn(name = "usuario_fechamento_id")
    private Usuario usuarioFechamento;
}
	

