package com.aro.FechamentoAro.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aro.FechamentoAro.dto.FechamentoMotoboyDTO;
import com.aro.FechamentoAro.dto.TotalEntregasDTO;
import com.aro.FechamentoAro.entities.FechamentoMotoboy;
import com.aro.FechamentoAro.entities.Motoboy;
import com.aro.FechamentoAro.exceptions.EntityNotFoundException;
import com.aro.FechamentoAro.repository.FechamentoMotoboyRepository;
import com.aro.FechamentoAro.repository.MotoboyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FechamentoMotoboyService {
    @Autowired
    private FechamentoMotoboyRepository repository;
    
    @Autowired
    private MotoboyRepository motoboyRepository;
    
    @Autowired
    private EntregaService entregasService;
    
    @Transactional
    public FechamentoMotoboy fecharPeriodo(FechamentoMotoboyDTO dto) {
        Motoboy motoboy = motoboyRepository.findById(dto.getMotoboyId())
                .orElseThrow(() -> new EntityNotFoundException("Motoboy não encontrado"));

        FechamentoMotoboy fechamento = new FechamentoMotoboy();
        fechamento.setMotoboy(motoboy);
        fechamento.setData(dto.getData());
        fechamento.setValorRecebido(dto.getValorRecebido());
        fechamento.setDespesas(dto.getDespesas());
        fechamento.setRetiradas(dto.getRetiradas());
        
        // Cálculo inicial do saldo
        BigDecimal saldo = dto.getValorRecebido()
                .subtract(dto.getDespesas())
                .subtract(dto.getRetiradas());
        
        // Obter o total das entregas (assumindo que TotalEntregasDTO tem um método getTotal())
        TotalEntregasDTO totalEntregas = entregasService.calcularTotalEntregas(motoboy.getId());
        saldo = saldo.add(totalEntregas.getTotal()); // Adiciona o valor das entregas ao saldo
        
        fechamento.setSaldoFinal(saldo);

        return repository.save(fechamento);
    }
    
    public Optional<FechamentoMotoboy> buscarUltimoFechamento(Long motoboyId) {
        return repository.findFirstByMotoboyIdOrderByDataDesc(motoboyId);
    }
    
    public List<FechamentoMotoboy> listarPorMotoboy(Long motoboyId) {
        return repository.findByMotoboyId(motoboyId);
    }
    
    public List<FechamentoMotoboy> listarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return repository.findByDataBetween(dataInicio, dataFim);
    }
   
}
