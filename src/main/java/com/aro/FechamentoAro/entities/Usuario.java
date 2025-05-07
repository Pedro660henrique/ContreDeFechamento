package com.aro.FechamentoAro.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private String nome;
	
	@Column(unique = true)
	private String email;
	
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private Nivel nivel;
	
	@OneToMany(mappedBy = "usuarioAbertura")
	@ToString.Exclude
    private List<FechamentoCaixa> fechamentosAbertos;

    @OneToMany(mappedBy = "usuarioFechamento")
    @ToString.Exclude
    private List<FechamentoCaixa> fechamentosFechados;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Entrada> entradasRegistradas;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Saida> saidasRegistradas;
    
    @Column(name = "tentativas_login")
    private int tentativasLogin = 0;

    @Column(name = "conta_bloqueada")
    private boolean contaBloqueada = false;

    @Column(name = "data_desbloqueio")
    private LocalDateTime dataDesbloqueio;
	
	private boolean ativo = true;
	
	public void incrementarTentativasLogin() {
        this.tentativasLogin++;
    }
    
    public void resetarTentativasLogin() {
        this.tentativasLogin = 0;
    }
    
    public void bloquearConta(LocalDateTime dataDesbloqueio) {
        this.contaBloqueada = true;
        this.dataDesbloqueio = dataDesbloqueio;
    }
    
    public void desbloquearConta() {
        this.contaBloqueada = false;
        this.dataDesbloqueio = null;
        this.tentativasLogin = 0;
    }
    
    public boolean isContaBloqueada() {
        if (this.contaBloqueada && this.dataDesbloqueio != null 
            && LocalDateTime.now().isAfter(this.dataDesbloqueio)) {
            desbloquearConta();
            return false;
        }
        return this.contaBloqueada;
    }
}
