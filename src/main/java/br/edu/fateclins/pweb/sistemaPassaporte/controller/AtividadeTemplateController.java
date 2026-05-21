package br.edu.fateclins.pweb.sistemaPassaporte.controller;

import br.edu.fateclins.pweb.sistemaPassaporte.dto.AtividadeTemplateRequestDTO;
import br.edu.fateclins.pweb.sistemaPassaporte.service.AtividadeTemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/templates/atividades")
public class AtividadeTemplateController {

    private final AtividadeTemplateService service;

    public AtividadeTemplateController(AtividadeTemplateService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> cadastrarAtividade(@RequestBody AtividadeTemplateRequestDTO request) {

        // Delega o processamento pesado para o Service
        service.cadastrarAtividadeNoMolde(request);

        // Retorna o Status 201 (Created) informando o sucesso da operação
        return ResponseEntity.status(HttpStatus.CREATED).body("Atividade cadastrada no molde com sucesso!");
    }
}