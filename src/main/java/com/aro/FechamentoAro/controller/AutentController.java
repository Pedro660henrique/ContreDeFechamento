package com.aro.FechamentoAro.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aro.FechamentoAro.Security.Config.exceptions.AutenticacaoException;
import com.aro.FechamentoAro.Security.Config.exceptions.SenhaInvalidaException;
import com.aro.FechamentoAro.Security.Config.exceptions.UsuarioNaoEncontradoException;
import com.aro.FechamentoAro.dto.AutentDTO;
import com.aro.FechamentoAro.dto.TokenResponseDTO;
import com.aro.FechamentoAro.entities.Usuario;
import com.aro.FechamentoAro.repository.UsuarioRepository;
import com.aro.FechamentoAro.service.JwtService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AutentController {

	@Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody AutentDTO request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.warn("Tentativa de login com email não cadastrado: {}", request.getEmail());
                    return new UsuarioNaoEncontradoException("Credenciais inválidas");
                });
        
        verificarContaBloqueada(usuario);
        validarSenha(usuario, request.getSenha());
        
        usuario.resetarTentativasLogin();
        usuarioRepository.save(usuario);
        
        String token = jwtService.gerarToken(usuario);
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }
    
    private void verificarContaBloqueada(Usuario usuario) {
        if (usuario.isContaBloqueada()) {
            if (usuario.getDataDesbloqueio().isAfter(LocalDateTime.now())) {
                log.warn("Tentativa de login em conta bloqueada: {}", usuario.getEmail());
                throw new AutenticacaoException("Conta bloqueada temporariamente. Tente novamente após " 
                    + usuario.getDataDesbloqueio());
            }
            usuario.desbloquearConta();
        }
    }

    private void validarSenha(Usuario usuario, String senha) {
        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            usuario.incrementarTentativasLogin();
            
            if (usuario.getTentativasLogin() >= 3) {
                usuario.bloquearConta(LocalDateTime.now().plusHours(1));
                usuarioRepository.save(usuario);
                log.warn("Conta bloqueada para: {} devido a muitas tentativas", usuario.getEmail());
                throw new AutenticacaoException("Conta bloqueada por 1 hora devido a muitas tentativas falhas");
            }
            
            usuarioRepository.save(usuario);
            throw new SenhaInvalidaException("Credenciais inválidas");
        }
    }
  }
    
    
