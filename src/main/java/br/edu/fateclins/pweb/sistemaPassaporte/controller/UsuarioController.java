package br.edu.fateclins.pweb.sistemaPassaporte.controller;

import br.edu.fateclins.pweb.sistemaPassaporte.dto.UsuarioRequestDTO;
import br.edu.fateclins.pweb.sistemaPassaporte.dto.UsuarioResponseDTO;
import br.edu.fateclins.pweb.sistemaPassaporte.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioRequestDTO request) {

        // Chama o maestro
        UsuarioResponseDTO response = usuarioService.cadastrarUsuario(request);

        // Devolve o status 201 (Criado) com os dados do usuário salvo
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}