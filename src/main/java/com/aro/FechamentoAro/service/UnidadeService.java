package com.aro.FechamentoAro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aro.FechamentoAro.dto.UnidadeDTO;
import com.aro.FechamentoAro.entities.Unidade;
import com.aro.FechamentoAro.exceptions.EntityNotFoundException;
import com.aro.FechamentoAro.repository.UnidadeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnidadeService {
	
	@Autowired
    private UnidadeRepository unidadeRepository;
    
	public UnidadeDTO criar(UnidadeDTO unidadeDTO) {
        Unidade unidade = new Unidade();
        unidade.setNome(unidadeDTO.getNome());
        unidade.setAtiva(unidadeDTO.isAtiva());
        
        Unidade savedUnidade = unidadeRepository.save(unidade);
        return convertToDTO(savedUnidade);
    }

    public Optional<UnidadeDTO> buscarPorId(Long id) {
        return unidadeRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<UnidadeDTO> buscarPorNome(String nome) {
        return unidadeRepository.findByNome(nome)
                .map(this::convertToDTO);
    }

    public List<UnidadeDTO> listarAtivas() {
        return unidadeRepository.findByAtivaTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void atualizarStatus(Long id, boolean ativa) {
        Unidade unidade = unidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada"));
        unidade.setAtiva(ativa);
        unidadeRepository.save(unidade);
    }

    private UnidadeDTO convertToDTO(Unidade unidade) {
        return UnidadeDTO.builder()
                .id(unidade.getId())
                .nome(unidade.getNome())
                .ativa(unidade.isAtiva())
                .build();
    }

}
