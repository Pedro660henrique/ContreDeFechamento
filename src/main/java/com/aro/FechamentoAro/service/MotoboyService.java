package com.aro.FechamentoAro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aro.FechamentoAro.entities.Motoboy;
import com.aro.FechamentoAro.exceptions.EntityNotFoundException;
import com.aro.FechamentoAro.repository.MotoboyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MotoboyService {
	
	@Autowired
    private MotoboyRepository motoboyRepository;

    public List<Motoboy> listarTodos() {
        return motoboyRepository.findAll();
    }

    public Motoboy buscarPorId(Long id) {
    	return motoboyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Motoboy não encontrado"));
    }

    public Motoboy salvar(Motoboy motoboy) {
        return motoboyRepository.save(motoboy);
    }

    public void deletar(Long id) {
    	if (!motoboyRepository.existsById(id)) {
            throw new EntityNotFoundException("Motoboy não encontrado");
        }
    	motoboyRepository.deleteById(id);
    }

}
