package com.aro.FechamentoAro.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Column(unique = true)
	private String email;
	
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private Nivel nivel;
	
	@OneToMany(mappedBy = "usuarioAbertura")
    private List<FechamentoCaixa> fechamentosAbertos;

    @OneToMany(mappedBy = "usuarioFechamento")
    private List<FechamentoCaixa> fechamentosFechados;

    @OneToMany(mappedBy = "usuario")
    private List<Entrada> entradasRegistradas;

    @OneToMany(mappedBy = "usuario")
    private List<Saida> saidasRegistradas;
	
	private boolean ativo = true;
}
