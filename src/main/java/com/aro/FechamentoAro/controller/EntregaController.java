package com.aro.FechamentoAro.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aro.FechamentoAro.entities.Entregas;
import com.aro.FechamentoAro.service.EntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	@Autowired
    private EntregaService entregasService;

    /**
     * Adiciona uma nova entrega
     */
    @PostMapping("/adicionar")
    public ResponseEntity<Entregas> adicionarEntrega(@RequestParam Long motoboyId,
                                                    @RequestParam Long fechamentoMotoboyId,
                                                    @RequestParam String numeroEntrega,
                                                    @RequestParam BigDecimal valor) {
        Entregas entrega = entregasService.adicionarEntrega(motoboyId, fechamentoMotoboyId, numeroEntrega, valor);
        return ResponseEntity.ok(entrega);
    }

    /**
     * Retorna o total das entregas de um motoboy em um fechamento específico
     */
    @GetMapping("/total/{fechamentoMotoboyId}")
    public ResponseEntity<BigDecimal> contarEntregasPorFechamento(@PathVariable Long fechamentoMotoboyId) {
        BigDecimal total = entregasService.contarEntregasPorFechamento(fechamentoMotoboyId);
        return ResponseEntity.ok(total);
    }
}