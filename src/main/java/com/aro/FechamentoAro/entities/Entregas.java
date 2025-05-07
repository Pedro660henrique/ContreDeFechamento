package com.aro.FechamentoAro.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Entregas implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column(unique = true, nullable = false)
    @NotBlank(message = "Número da entrega é obrigatório")
    @Size(max = 50, message = "Número da entrega deve ter no máximo 50 caracteres")
	private String numeroEntrega;
	
	@Column(nullable = false, precision = 10, scale = 2)
	@NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor mínimo é 0.01")
	private BigDecimal valor;
	
	@Column(name = "data_registro", nullable = false)
    @NotNull
    private LocalDateTime dataRegistro;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fechamento_motoboy_id", nullable = false)
    @NotNull(message = "Fechamento é obrigatório")
    private FechamentoMotoboy fechamentoMotoboy;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motoboy_id", nullable = false)
    @NotNull(message = "Motoboy é obrigatório")
	private Motoboy motoboy;
	
	@PrePersist
    protected void onCreate() {
        this.dataRegistro = LocalDateTime.now();
    }
	
}
