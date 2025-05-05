package com.aro.FechamentoAro.repository;

import com.aro.FechamentoAro.entities.Entregas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntregaRepository extends JpaRepository<Entregas, Long>{
		
	List<Entregas> findByFechamentoMotoboyId(Long fechamentoMotoboyId);
    Optional<Entregas> findByNumeroEntrega(String numeroEntrega);
}
