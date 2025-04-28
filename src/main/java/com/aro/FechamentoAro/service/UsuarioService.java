package com.aro.FechamentoAro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aro.FechamentoAro.entities.Usuario;
import com.aro.FechamentoAro.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

	public Optional<Usuario> buscarPorEmail(String email) {
		return usuarioRepository.buscarPorEmail(email);
	}

	public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
	    Usuario usuarioExistente = usuarioRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));

	    usuarioExistente.setNome(usuarioAtualizado.getNome());
	    usuarioExistente.setEmail(usuarioAtualizado.getEmail());
	    usuarioExistente.setSenha(usuarioAtualizado.getSenha());
	    usuarioExistente.setNivel(usuarioAtualizado.getNivel());
	    usuarioExistente.setAtivo(usuarioAtualizado.isAtivo());

	    return usuarioRepository.save(usuarioExistente);
	}
}
