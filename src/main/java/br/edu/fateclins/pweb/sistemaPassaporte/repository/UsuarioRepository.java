package br.edu.fateclins.pweb.sistemaPassaporte.repository;

import br.edu.fateclins.pweb.sistemaPassaporte.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
