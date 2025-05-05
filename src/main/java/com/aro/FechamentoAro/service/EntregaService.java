package com.aro.FechamentoAro.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aro.FechamentoAro.entities.Entregas;
import com.aro.FechamentoAro.entities.FechamentoMotoboy;
import com.aro.FechamentoAro.entities.Motoboy;
import com.aro.FechamentoAro.exceptions.EntityNotFoundException;
import com.aro.FechamentoAro.repository.EntregaRepository;
import com.aro.FechamentoAro.repository.FechamentoMotoboyRepository;
import com.aro.FechamentoAro.repository.MotoboyRepository;

import jakarta.transaction.Transactional;

@Service
public class EntregaService {
	
	 @Autowired
	    private EntregaRepository entregasRepository;
	    
	    @Autowired
	    private MotoboyRepository motoboyRepository;

	    @Autowired
	    private FechamentoMotoboyRepository fechamentoMotoboyRepository;

	    /**
	     * Adiciona uma nova entrega ao fechamento do motoboy
	     */
	    @Transactional
	    public Entregas adicionarEntrega(Long motoboyId, Long fechamentoMotoboyId, String numeroEntrega, BigDecimal valor) {
	        Motoboy motoboy = motoboyRepository.findById(motoboyId)
	                .orElseThrow(() -> new EntityNotFoundException("Motoboy não encontrado"));

	        FechamentoMotoboy fechamentoMotoboy = fechamentoMotoboyRepository.findById(fechamentoMotoboyId)
	                .orElseThrow(() -> new EntityNotFoundException("Fechamento de Motoboy não encontrado"));

	        Entregas entrega = new Entregas();
	        entrega.setNumeroEntrega(numeroEntrega);
	        entrega.setValor(valor);
	        entrega.setMotoboy(motoboy);
	        entrega.setFechamentoMotoboy(fechamentoMotoboy);

	        return entregasRepository.save(entrega);
	    }

	    /**
	     * Conta o total das entregas de um motoboy em um fechamento específico
	     */
	    public BigDecimal contarEntregasPorFechamento(Long fechamentoMotoboyId) {
	        List<Entregas> entregas = entregasRepository.findByFechamentoMotoboyId(fechamentoMotoboyId);
	        return entregas.stream()
	                .map(Entregas::getValor)
	                .reduce(BigDecimal.ZERO, BigDecimal::add);
	    }
	}