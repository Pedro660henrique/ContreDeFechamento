package com.aro.FechamentoAro.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aro.FechamentoAro.entities.FechamentoCaixa;
import com.aro.FechamentoAro.entities.Unidade;

@Repository
public interface FechamentoCaixaRepository extends JpaRepository<FechamentoCaixa, Long> {
	Optional<FechamentoCaixa> findByUnidadeAndData(Unidade unidade, LocalDate data);
	Optional<FechamentoCaixa> findByUnidadeAndHorarioFechamentoIsNull(Unidade unidade);
	List<FechamentoCaixa> findByDataBetween(LocalDate inicio, LocalDate fim);
	List<FechamentoCaixa> findByUnidadeIdAndDataBetween(Long unidadeId, LocalDate dataInicio, LocalDate dataFim);
	List<FechamentoCaixa> findByHorarioFechamentoIsNull();
}
