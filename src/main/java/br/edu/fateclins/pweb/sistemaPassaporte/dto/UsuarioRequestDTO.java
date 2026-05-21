package br.edu.fateclins.pweb.sistemaPassaporte.dto;

import br.edu.fateclins.pweb.sistemaPassaporte.model.PerfilUsuarioEnum;

public record UsuarioRequestDTO(
        String nome,
        String email,
        String senha,
        PerfilUsuarioEnum perfil,
        String chaveAcesso
) {
}
