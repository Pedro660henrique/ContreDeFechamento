
package com.aro.FechamentoAro.entities;

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

@Entity
@Data
public class FechamentoCaixa {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate data;
	private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;
    private BigDecimal inicioTroco;
    private BigDecimal terminoTroco;
	
	@ManyToOne
	private Unidade unidade;
	
    // Relacionamentos com as movimentações detalhadas
	@OneToMany(mappedBy = "fechamentoCaixa", cascade = CascadeType.ALL)
	private List<Entrada> entradas;
	
	@OneToMany(mappedBy = "fechamentoCaixa", cascade = CascadeType.ALL)
	private List<Saida> saidas;
	
	@OneToMany(mappedBy = "fechamentoCaixa", cascade = CascadeType.ALL)
	private List<AnotacaoDespesa> anotacoes;
	
	@ManyToOne
    @JoinColumn(name = "usuario_abertura_id")
    private Usuario usuarioAbertura;

    @ManyToOne
    @JoinColumn(name = "usuario_fechamento_id")
    private Usuario usuarioFechamento;
}
	

