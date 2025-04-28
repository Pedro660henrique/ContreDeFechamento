package com.aro.FechamentoAro.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aro.FechamentoAro.entities.Entrada;
import com.aro.FechamentoAro.entities.MetodoPagamento;

public interface EntradaRepository extends JpaRepository<Entrada, Long>{
	List<Entrada> findByFechamentoCaixaId(Long fechamentoId);
    List<Entrada> findByMetodoPagamento(MetodoPagamento metodo);
    BigDecimal sumValorByFechamentoCaixaId(Long fechamentoId);
}
