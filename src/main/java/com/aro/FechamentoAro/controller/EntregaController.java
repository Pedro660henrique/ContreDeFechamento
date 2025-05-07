package com.aro.FechamentoAro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aro.FechamentoAro.dto.EntregaDTO;
import com.aro.FechamentoAro.dto.TotalEntregasDTO;
import com.aro.FechamentoAro.service.EntregaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/entregas")
@RequiredArgsConstructor
@Tag(name = "Entregas", description = "Operações relacionadas a entregas")
public class EntregaController {
	
	@Autowired
    private EntregaService entregasService;

    
    //Adiciona uma nova entrega
	@PostMapping
    @Operation(summary = "Adiciona uma nova entrega")
    public ResponseEntity<EntregaDTO> adicionarEntrega(@RequestBody @Valid EntregaDTO request) {
        return ResponseEntity.ok(entregasService.adicionarEntrega(request));
    }
    
    //Retorna o total das entregas de um motoboy em um fechamento específico
	@GetMapping("/total/{fechamentoMotoboyId}")
    @Operation(summary = "Calcula o total das entregas de um fechamento")
    public ResponseEntity<TotalEntregasDTO> calcularTotalEntregas(
            @PathVariable Long fechamentoMotoboyId) {
        return ResponseEntity.ok(entregasService.calcularTotalEntregas(fechamentoMotoboyId));
    }

}