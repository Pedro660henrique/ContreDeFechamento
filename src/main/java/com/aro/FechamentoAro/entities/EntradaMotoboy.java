package com.aro.FechamentoAro.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class EntradaMotoboy implements Serializable{
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
    private String descricao;
    
    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    private BigDecimal valor;
    
    @Column(name = "data_hora", nullable = false)
    @NotNull(message = "Data e hora são obrigatórias")
    private LocalDateTime dataHora;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motoboy_id", nullable = false)
    private Motoboy motoboy;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fechamento_motoboy_id")
    private FechamentoMotoboy fechamentoMotoboy;
    
    @Transient
    public LocalDate getData() {
        return dataHora != null ? dataHora.toLocalDate() : null;
    }
}