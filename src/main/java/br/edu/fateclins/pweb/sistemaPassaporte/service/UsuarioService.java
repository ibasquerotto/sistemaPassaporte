package br.edu.fateclins.pweb.sistemaPassaporte.service;

import br.edu.fateclins.pweb.sistemaPassaporte.dto.UsuarioRequestDTO;
import br.edu.fateclins.pweb.sistemaPassaporte.dto.UsuarioResponseDTO;
import br.edu.fateclins.pweb.sistemaPassaporte.model.Usuario;
import br.edu.fateclins.pweb.sistemaPassaporte.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO dto) {

        // 1. Transformar o DTO (pacote da internet) em uma Entidade (Tabela do banco)
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setEmail(dto.email());
        novoUsuario.setSenha(dto.senha()); // Em um sistema real, aqui usaríamos um BCrypt para criptografar
        novoUsuario.setPerfil(dto.perfil());
        novoUsuario.setChaveAcesso(dto.chaveAcesso());

        // 2. O Spring Data JPA salva no banco (gera o INSERT)
        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);

        // 3. Montamos o pacote de resposta (sem a senha!)
        return new UsuarioResponseDTO(
                (long) usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getEmail(),
                usuarioSalvo.getPerfil()
        );
    }
}