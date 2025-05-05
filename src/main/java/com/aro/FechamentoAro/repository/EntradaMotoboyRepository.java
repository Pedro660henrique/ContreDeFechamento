package com.aro.FechamentoAro.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aro.FechamentoAro.entities.EntradaMotoboy;

@Repository
public interface EntradaMotoboyRepository extends JpaRepository<EntradaMotoboy, Long> {
	List<EntradaMotoboy> findByMotoboyId(Long motoboyId);
	
	List<EntradaMotoboy> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
}