package com.aro.FechamentoAro.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aro.FechamentoAro.dto.FechamentoCaixaDTO;
import com.aro.FechamentoAro.entities.FechamentoCaixa;
import com.aro.FechamentoAro.entities.FechamentoMotoboy;
import com.aro.FechamentoAro.entities.Unidade;
import com.aro.FechamentoAro.entities.Usuario;
import com.aro.FechamentoAro.exceptions.BusinessException;
import com.aro.FechamentoAro.exceptions.EntityNotFoundException;
import com.aro.FechamentoAro.repository.FechamentoCaixaRepository;
import com.aro.FechamentoAro.repository.FechamentoMotoboyRepository;
import com.aro.FechamentoAro.repository.UnidadeRepository;
import com.aro.FechamentoAro.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class FechamentoCaixaService {

    @Autowired
    private FechamentoCaixaRepository fechamentoCaixaRepository;
    
    @Autowired
    private UnidadeRepository unidadeRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private FechamentoMotoboyRepository fechamentoMotoboyRepository;

    /**
     * Abre um novo caixa para a unidade especificada
     */
    @Transactional
    public FechamentoCaixa abrirCaixa(FechamentoCaixaDTO dto) {
     
        if (dto.getInicioTroco() == null || dto.getInicioTroco().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("O troco inicial deve ser um valor positivo");
        }

        // Busca a unidade
        Unidade unidade = unidadeRepository.findById(dto.getUnidadeId())
            .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada com ID: " + dto.getUnidadeId()));

        // Verifica se já existe caixa aberto
        Optional<FechamentoCaixa> caixaAberto = fechamentoCaixaRepository
            .findByUnidadeAndHorarioFechamentoIsNull(unidade);
        
        if (caixaAberto.isPresent()) {
            throw new BusinessException("Já existe um caixa aberto para esta unidade");
        }

        // Obtém o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(username)
            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        // Cria o novo caixa
        FechamentoCaixa novoCaixa = new FechamentoCaixa();
        novoCaixa.setData(LocalDate.now());
        novoCaixa.setHorarioAbertura(LocalTime.now());
        novoCaixa.setInicioTroco(dto.getInicioTroco());
        novoCaixa.setUnidade(unidade);
        novoCaixa.setUsuarioAbertura(usuario);
        novoCaixa.setEntradas(new ArrayList<>());
        novoCaixa.setSaidas(new ArrayList<>());
        novoCaixa.setAnotacoes(new ArrayList<>());

        return fechamentoCaixaRepository.save(novoCaixa);
    }

    /**
     * Fecha um caixa aberto
     */
 
    @Transactional
    public FechamentoCaixa fecharCaixa(Long id, BigDecimal trocoFinal) {
        // Primeiro obtém o caixa
        FechamentoCaixa caixa = fechamentoCaixaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Caixa não encontrado com ID: " + id));

        if (caixa.getHorarioFechamento() != null) {
            throw new BusinessException("Este caixa já está fechado");
        }

        // Agora que temos o caixa, podemos usar caixa.getUnidade()
        List<FechamentoMotoboy> fechamentosMotoboys = fechamentoMotoboyRepository
            .findByUnidade_IdAndData(caixa.getUnidade().getId(), LocalDate.now());
        
        BigDecimal totalFechamentoMotoboy = BigDecimal.ZERO;

        for (FechamentoMotoboy fechamento : fechamentosMotoboys) {
            totalFechamentoMotoboy = totalFechamentoMotoboy.add(fechamento.getSaldoFinal());
        }

        // Obtém o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(username)
            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        // Atualizar o fechamento do caixa
        caixa.setTerminoTroco(trocoFinal);
        caixa.setTotalFechamento(totalFechamentoMotoboy);
        caixa.setUsuarioFechamento(usuario);
        caixa.setHorarioFechamento(LocalTime.now());

        return fechamentoCaixaRepository.save(caixa);
    }

    /**
     * Busca um fechamento de caixa por ID
     */
    public Optional<FechamentoCaixa> buscarPorId(Long id) {
        return fechamentoCaixaRepository.findById(id);
    }

    /**
     * Lista todos os fechamentos de caixa de uma unidade
     */
    public Optional<FechamentoCaixa> listarPorUnidade(Long unidadeId) {
        return fechamentoCaixaRepository.findById(unidadeId);
    }

    /**
     * Lista fechamentos por período
     */
    public List<FechamentoCaixa> listarPorPeriodo(Long unidadeId, LocalDate dataInicio, LocalDate dataFim) {
        return fechamentoCaixaRepository.findByUnidadeIdAndDataBetween(unidadeId, dataInicio, dataFim);
    }
    public List<FechamentoCaixa> listarTodos() {
        return fechamentoCaixaRepository.findAll();
    }
    
    public List<FechamentoCaixa> listarCaixasAbertos() {
        return fechamentoCaixaRepository.findByHorarioFechamentoIsNull();
    }
}
