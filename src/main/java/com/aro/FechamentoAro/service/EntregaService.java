package com.aro.FechamentoAro.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aro.FechamentoAro.dto.EntregaDTO;
import com.aro.FechamentoAro.dto.TotalEntregasDTO;
import com.aro.FechamentoAro.entities.Entregas;
import com.aro.FechamentoAro.entities.FechamentoMotoboy;
import com.aro.FechamentoAro.entities.Motoboy;
import com.aro.FechamentoAro.exceptions.EntityNotFoundException;
import com.aro.FechamentoAro.repository.EntregaRepository;
import com.aro.FechamentoAro.repository.FechamentoMotoboyRepository;
import com.aro.FechamentoAro.repository.MotoboyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntregaService {
	
	 	@Autowired
	    private EntregaRepository entregasRepository;
	    
	    @Autowired
	    private MotoboyRepository motoboyRepository;

	    @Autowired
	    private FechamentoMotoboyRepository fechamentoMotoboyRepository;

	    
	    //Adiciona uma nova entrega ao fechamento do motoboy
	    @Transactional
	    public EntregaDTO adicionarEntrega(EntregaDTO dto) {
	        Motoboy motoboy = motoboyRepository.findById(dto.getMotoboyId())
	                .orElseThrow(() -> new EntityNotFoundException("Motoboy não encontrado"));

	        FechamentoMotoboy fechamento = fechamentoMotoboyRepository.findById(dto.getFechamentoMotoboyId())
	                .orElseThrow(() -> new EntityNotFoundException("Fechamento de Motoboy não encontrado"));

	        Entregas entrega = new Entregas();
	        entrega.setNumeroEntrega(dto.getNumeroEntrega());
	        entrega.setValor(dto.getValor());
	        entrega.setMotoboy(motoboy);
	        entrega.setFechamentoMotoboy(fechamento);

	        Entregas saved = entregasRepository.save(entrega);
	        return toDTO(saved);
	    }

	    
	     //Conta o total das entregas de um motoboy em um fechamento específico
	    public TotalEntregasDTO calcularTotalEntregas(Long fechamentoMotoboyId) {
	        List<Entregas> entregas = entregasRepository.findByFechamentoMotoboyId(fechamentoMotoboyId);
	        
	        BigDecimal total = entregas.stream()
	                .map(Entregas::getValor)
	                .reduce(BigDecimal.ZERO, BigDecimal::add);
	        
	        return TotalEntregasDTO.builder()
	                .fechamentoMotoboyId(fechamentoMotoboyId)
	                .total(total)
	                .quantidade(entregas.size())
	                .build();
	    }

	    private EntregaDTO toDTO(Entregas entrega) {
	        return EntregaDTO.builder()
	                .numeroEntrega(entrega.getNumeroEntrega())
	                .valor(entrega.getValor())
	                .motoboyId(entrega.getMotoboy().getId())
	                .fechamentoMotoboyId(entrega.getFechamentoMotoboy().getId())
	                .build();
	    }
	}