package br.edu.fateclins.pweb.sistemaPassaporte.repository;

import br.edu.fateclins.pweb.sistemaPassaporte.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa,Long> {
}
