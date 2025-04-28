package com.aro.FechamentoAro.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Motoboy {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private boolean ativo;

    @OneToMany(mappedBy = "motoboy", cascade = CascadeType.ALL)
    private List<FechamentoMotoboy> fechamentos;
}
