package com.aro.FechamentoAro.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aro.FechamentoAro.entities.FechamentoMotoboy;

@Repository
public interface FechamentoMotoboyRepository extends JpaRepository<FechamentoMotoboy, Long> {
	List<FechamentoMotoboy> findByMotoboyId(Long motoboyId);
    Optional<FechamentoMotoboy> findFirstByMotoboyIdOrderByDataDesc(Long motoboyId);
	List<FechamentoMotoboy> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);
}
