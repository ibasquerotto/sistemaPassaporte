package br.edu.fateclins.pweb.sistemaPassaporte.controller;

import br.edu.fateclins.pweb.sistemaPassaporte.dto.AtividadeResponseDTO;
import br.edu.fateclins.pweb.sistemaPassaporte.service.AtividadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {

    private final AtividadeService atividadeService;

    // Injeção de dependência do nosso Service
    public AtividadeController(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

    @GetMapping("/passaporte/{passaporteId}")
    public ResponseEntity<List<AtividadeResponseDTO>> listarPorPassaporte(@PathVariable Long passaporteId) {

        // O Controller apenas delega a inteligência para o Service
        List<AtividadeResponseDTO> atividades = atividadeService.listarAtividadesPorPassaporte(passaporteId);

        // Retorna a lista encapsulada em um ResponseEntity com o status HTTP 200 (OK)
        return ResponseEntity.ok(atividades);
    }
}