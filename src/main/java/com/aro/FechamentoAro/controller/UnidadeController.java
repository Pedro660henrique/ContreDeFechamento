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

import com.aro.FechamentoAro.entities.Unidade;
import com.aro.FechamentoAro.service.UnidadeService;

@RestController
@RequestMapping("/api/unidades")
public class UnidadeController {
	
    @Autowired
    private UnidadeService unidadeService;
    
    @PostMapping
    public ResponseEntity<Unidade> criar(@RequestBody Unidade unidade) {
        return ResponseEntity.ok(unidadeService.criarUnidade(unidade));
    }
    
    @GetMapping("/por-nome")
    public ResponseEntity<Unidade> buscarPorNome(@RequestParam String nome) {
        return unidadeService.buscarPorNome(nome)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Unidade> buscarPorId(@PathVariable Long id) {
        return unidadeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/ativas")
    public List<Unidade> listarAtivas() {
        return unidadeService.listarAtivas();
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatus(
            @PathVariable Long id,
            @RequestParam boolean ativa) {
    	unidadeService.atualizarStatus(id, ativa);
        return ResponseEntity.noContent().build();
    }

}
