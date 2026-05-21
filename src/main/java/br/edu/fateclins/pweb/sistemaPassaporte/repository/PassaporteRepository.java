package br.edu.fateclins.pweb.sistemaPassaporte.repository;

import br.edu.fateclins.pweb.sistemaPassaporte.model.Passaporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassaporteRepository extends JpaRepository<Passaporte, Long> {


    List<Passaporte> findByCandidatoId(Long candidatoId);
}
