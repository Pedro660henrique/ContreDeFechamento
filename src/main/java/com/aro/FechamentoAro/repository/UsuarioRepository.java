package com.aro.FechamentoAro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aro.FechamentoAro.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findByEmail(String email);
	boolean existsByEmail(String email);
	Optional<Usuario> buscarPorEmail(String email);
}
