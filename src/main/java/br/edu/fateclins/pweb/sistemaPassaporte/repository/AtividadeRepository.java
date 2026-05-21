package br.edu.fateclins.pweb.sistemaPassaporte.repository;


import br.edu.fateclins.pweb.sistemaPassaporte.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    List<Atividade> findByPassaporteId(Long passaporteId);
}
