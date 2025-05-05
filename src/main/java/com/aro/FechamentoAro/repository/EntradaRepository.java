package com.aro.FechamentoAro.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aro.FechamentoAro.entities.Entrada;
import com.aro.FechamentoAro.entities.MetodoPagamento;

public interface EntradaRepository extends JpaRepository<Entrada, Long>{
	List<Entrada> findByFechamentoCaixaId(Long fechamentoId);
    List<Entrada> findByMetodoPagamento(MetodoPagamento metodo);
    
    @Query("SELECT SUM(e.valor) FROM Entrada e WHERE e.fechamentoCaixa.id = :fechamentoCaixaId")
    BigDecimal sumValorByFechamentoCaixaId(@Param("fechamentoCaixaId") Long fechamentoCaixaId);
}
