package com.aro.FechamentoAro.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aro.FechamentoAro.dto.FechamentoMotoboyDTO;
import com.aro.FechamentoAro.entities.FechamentoMotoboy;
import com.aro.FechamentoAro.service.FechamentoMotoboyService;

@RestController
@RequestMapping("/api/fechamentos-motoboy")
public class FechamentoMotoboyController {
	
    @Autowired
    private FechamentoMotoboyService service;
    
    @PostMapping
    public ResponseEntity<FechamentoMotoboy> fecharPeriodo(@RequestBody FechamentoMotoboyDTO dto) {
    	FechamentoMotoboy fechamento = service.fecharPeriodo(dto);
        return ResponseEntity.ok(fechamento);
    }
    
    @GetMapping("/motoboy/{motoboyId}")
    public List<FechamentoMotoboy> listarPorMotoboy(@PathVariable Long motoboyId) {
        return service.listarPorMotoboy(motoboyId);
    }
    
    @GetMapping("/ultimo/{motoboyId}")
    public ResponseEntity<FechamentoMotoboy> buscarUltimoFechamento(@PathVariable Long motoboyId) {
        return service.buscarUltimoFechamento(motoboyId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
    
    @GetMapping("/periodo")
    public List<FechamentoMotoboy> listarPorPeriodo(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim) {
        return service.listarPorPeriodo(inicio, fim);
    }
}