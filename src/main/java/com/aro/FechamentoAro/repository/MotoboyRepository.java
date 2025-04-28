package com.aro.FechamentoAro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aro.FechamentoAro.entities.Motoboy;

@Repository
public interface MotoboyRepository extends JpaRepository<Motoboy, Long>{
	Optional<Motoboy> findByTelefone(String telefone);
	List<Motoboy> findByAtivoTrue();
    boolean existsByTelefone(String telefone);
}
