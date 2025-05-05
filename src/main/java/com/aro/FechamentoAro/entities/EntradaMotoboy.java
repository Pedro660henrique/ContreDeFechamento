package com.aro.FechamentoAro.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class EntradaMotoboy {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    
    @Column(name = "data_hora")
    private LocalDateTime dataHora;
    
    @ManyToOne
    private Motoboy motoboy;
    
    @ManyToOne
    private FechamentoMotoboy fechamentoMotoboy;
    
    @Transient
    public LocalDate getData() {
        return dataHora != null ? dataHora.toLocalDate() : null;
    }
}