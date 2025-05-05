package com.aro.FechamentoAro.Security.Config;

import com.aro.FechamentoAro.entities.Usuario;
import com.aro.FechamentoAro.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	 private final UsuarioRepository usuarioRepository;

	    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
	        this.usuarioRepository = usuarioRepository;
	    }

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        Usuario usuario = usuarioRepository.findByEmail(email)
	                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));
	        return org.springframework.security.core.userdetails.User
	                .withUsername(usuario.getEmail())
	                .password(usuario.getSenha())
	                .roles(usuario.getNivel().name()) // "ADMIN", "USER"
	                .build();
	    }

}
