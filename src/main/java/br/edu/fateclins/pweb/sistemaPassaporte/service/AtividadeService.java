package br.edu.fateclins.pweb.sistemaPassaporte.service;

import br.edu.fateclins.pweb.sistemaPassaporte.dto.AtividadeResponseDTO;
import br.edu.fateclins.pweb.sistemaPassaporte.model.Atividade;
import br.edu.fateclins.pweb.sistemaPassaporte.repository.AtividadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class AtividadeService {

    private final AtividadeRepository atividadeRepository;

    // Injeção de dependência via construtor (Boa prática recomendada pelo Spring)
    public AtividadeService(AtividadeRepository atividadeRepository) {
        this.atividadeRepository = atividadeRepository;


    }

    public List<AtividadeResponseDTO> listarAtividadesPorPassaporte(Long passaporteId) {

        // 1. Busca as entidades brutas do banco de dados através do ID do passaporte
        List<Atividade> entidades = atividadeRepository.findByPassaporteId(passaporteId);

        // 2. Transforma (mapeia) a lista de Entidades em uma lista de DTOs limpos
        return entidades.stream()
                .map(atividade -> new AtividadeResponseDTO(
                        atividade.getId(),
                        atividade.getNome(),
                        atividade.getStatus().name() // Converte o Enum para String para enviar à tela
                ))
                .collect(Collectors.toList());
    }

}
