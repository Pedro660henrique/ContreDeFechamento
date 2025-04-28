package com.aro.FechamentoAro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.aro.FechamentoAro.entities.Unidade;
import com.aro.FechamentoAro.repository.UnidadeRepository;

public class UnidadeService {
	
	@Autowired
    private UnidadeRepository unidadeRepository;
    
    public Unidade criarUnidade(Unidade unidade) {
        return unidadeRepository.save(unidade);
    }
    
    public Optional<Unidade> buscarPorNome(String nome) {
        return unidadeRepository.findByNome(nome);
    }
    
    public List<Unidade> listarAtivas() {
        return unidadeRepository.findByAtivaTrue();
    }

	public Optional<Unidade> buscarPorId(Long id) {
		return unidadeRepository.buscarPorId(id);
	}

	public void atualizarStatus(Long id, boolean ativa) {
		Unidade unidade = unidadeRepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Unidade não encontrada com id: " + id));
		    unidade.setAtiva(ativa);
		    unidadeRepository.save(unidade);
		
	}

}
