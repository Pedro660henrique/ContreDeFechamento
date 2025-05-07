package com.aro.FechamentoAro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aro.FechamentoAro.dto.AtualizarUsuarioDTO;
import com.aro.FechamentoAro.dto.CriarUsuarioDTO;
import com.aro.FechamentoAro.dto.NovaSenhaDTO;
import com.aro.FechamentoAro.entities.Usuario;
import com.aro.FechamentoAro.exceptions.BusinessException;
import com.aro.FechamentoAro.exceptions.EntityNotFoundException;
import com.aro.FechamentoAro.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
public class UsuarioService {
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario criar(CriarUsuarioDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Email já cadastrado");
        }
        
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setNivel(dto.getNivel());
        usuario.setAtivo(true);
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Long id, AtualizarUsuarioDTO dto) {
        Usuario usuario = buscarPorIdOuFalhar(id);
        
        if (!usuario.getEmail().equals(dto.getEmail()) && 
            usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Email já está em uso por outro usuário");
        }
        
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setNivel(dto.getNivel());
        return usuarioRepository.save(usuario);
    }

    public void atualizarSenha(Long id, NovaSenhaDTO dto) {
        Usuario usuario = buscarPorIdOuFalhar(id);
        
        if (!passwordEncoder.matches(dto.getSenhaAtual(), usuario.getSenha())) {
            throw new BusinessException("Senha atual incorreta");
        }
        
        usuario.setSenha(passwordEncoder.encode(dto.getNovaSenha()));
        usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
    public Usuario buscarPorIdOuFalhar(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}
	
}
