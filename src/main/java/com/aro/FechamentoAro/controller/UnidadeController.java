package com.aro.FechamentoAro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aro.FechamentoAro.dto.UnidadeDTO;
import com.aro.FechamentoAro.service.UnidadeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/unidades")
public class UnidadeController {
	
    @Autowired
    private UnidadeService unidadeService;
    
    @PostMapping
    public ResponseEntity<UnidadeDTO> criar(@RequestBody @Valid UnidadeDTO unidadeDTO) {
        UnidadeDTO novaUnidade = unidadeService.criar(unidadeDTO);
        return ResponseEntity.ok(novaUnidade);
    }

    @GetMapping("/por-nome")
    public ResponseEntity<UnidadeDTO> buscarPorNome(@RequestParam String nome) {
        return unidadeService.buscarPorNome(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeDTO> buscarPorId(@PathVariable Long id) {
        return unidadeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<UnidadeDTO>> listarAtivas() {
        List<UnidadeDTO> unidadesAtivas = unidadeService.listarAtivas();
        return ResponseEntity.ok(unidadesAtivas);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatus(
            @PathVariable Long id,
            @RequestParam boolean ativa) {
        unidadeService.atualizarStatus(id, ativa);
        return ResponseEntity.noContent().build();
    }
}
