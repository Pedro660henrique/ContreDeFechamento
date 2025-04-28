package com.aro.FechamentoAro.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aro.FechamentoAro.dto.FechamentoCaixaDTO;
import com.aro.FechamentoAro.entities.FechamentoCaixa;
import com.aro.FechamentoAro.service.FechamentoCaixaService;

@RestController
@RequestMapping("/api/fechamentos-caixa")
public class FechamentoCaixaController {
	
	@Autowired
    private FechamentoCaixaService service;
    
    @PostMapping("/abrir")
    public ResponseEntity<FechamentoCaixa> abrirCaixa(@RequestBody FechamentoCaixaDTO dto) {
        return ResponseEntity.ok(service.abrirCaixa(dto));
    }
    
    @PostMapping("/{id}/fechar")
    public ResponseEntity<FechamentoCaixa> fecharCaixa(
            @PathVariable Long id,
            @RequestParam BigDecimal trocoFinal) {
        return ResponseEntity.ok(service.fecharCaixa(id, trocoFinal));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FechamentoCaixa> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/unidade/{unidadeId}")
    public Optional<FechamentoCaixa> listarPorUnidade(
            @PathVariable Long unidadeId,
            @RequestParam(required = false) LocalDate dataInicio,
            @RequestParam(required = false) LocalDate dataFim) {
        return service.listarPorUnidade(unidadeId);
    }
    
    @GetMapping("/periodo")
    public List<FechamentoCaixa> listarPorPeriodo(
            @RequestParam(required = false) Long unidadeId,
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim) {
        return service.listarPorPeriodo(unidadeId, inicio, fim);
    }
    
    @GetMapping("/abertos")
    public List<FechamentoCaixa> listarCaixasAbertos() {
        return service.listarCaixasAbertos();
    }
    
    
}
