package br.com.alura.mvc.mudi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	Usuario findByUsername(String username);
}
