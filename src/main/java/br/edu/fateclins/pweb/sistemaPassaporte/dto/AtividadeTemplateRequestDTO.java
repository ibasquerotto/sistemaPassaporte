package br.edu.fateclins.pweb.sistemaPassaporte.dto;

import br.edu.fateclins.pweb.sistemaPassaporte.model.PerfilUsuarioEnum;

public record AtividadeTemplateRequestDTO(
        String nome,
        Integer ordem,
        PerfilUsuarioEnum perfilResponsavel,
        Long perfilPassaporteId
) {
}
