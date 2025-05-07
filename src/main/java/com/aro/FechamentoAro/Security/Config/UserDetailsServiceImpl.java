package com.aro.FechamentoAro.Security.Config;

import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aro.FechamentoAro.entities.Usuario;
import com.aro.FechamentoAro.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final LoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	if (loginAttemptService.isBlocked(email)) {
            throw new LockedException("Conta temporariamente bloqueada devido a muitas tentativas falhas");
        }
    	
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> {
                log.error("Usuário não encontrado com email: {}", email);
                return new UsernameNotFoundException("Usuário não encontrado com email: " + email);
            });
        
        return User.builder()
            .username(usuario.getEmail())
            .password(usuario.getSenha())
            .roles(usuario.getNivel().name())
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build();
    }
}