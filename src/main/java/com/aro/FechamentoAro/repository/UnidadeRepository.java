package com.aro.FechamentoAro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aro.FechamentoAro.entities.Unidade;

@Repository
public  interface UnidadeRepository extends JpaRepository<Unidade, Long>{
	Optional<Unidade> findByNome(String nome);
	List<Unidade> findByAtivaTrue();
	boolean existsByNome(String nome);
	Optional<Unidade> findById(Long id);
}
