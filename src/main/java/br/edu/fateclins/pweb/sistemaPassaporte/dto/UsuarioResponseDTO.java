package br.edu.fateclins.pweb.sistemaPassaporte.dto;

import br.edu.fateclins.pweb.sistemaPassaporte.model.PerfilUsuarioEnum;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        PerfilUsuarioEnum perfil
) {
}
