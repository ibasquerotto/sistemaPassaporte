package br.edu.fateclins.pweb.sistemaPassaporte.service;

import br.edu.fateclins.pweb.sistemaPassaporte.dto.AtividadeTemplateRequestDTO;
import br.edu.fateclins.pweb.sistemaPassaporte.model.AtividadeTemplate;
import br.edu.fateclins.pweb.sistemaPassaporte.model.PerfilPassaporte;
import br.edu.fateclins.pweb.sistemaPassaporte.repository.AtividadeTemplateRepository;
import br.edu.fateclins.pweb.sistemaPassaporte.repository.PerfilPassaporteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtividadeTemplateService {

    private final AtividadeTemplateRepository atividadeTemplateRepository;
    private final PerfilPassaporteRepository perfilPassaporteRepository;

    // Injeção dos dois repositórios necessários para cruzar os dados
    public AtividadeTemplateService(AtividadeTemplateRepository atividadeTemplateRepository,
                                    PerfilPassaporteRepository perfilPassaporteRepository) {
        this.atividadeTemplateRepository = atividadeTemplateRepository;
        this.perfilPassaporteRepository = perfilPassaporteRepository;
    }

    @Transactional
    public void cadastrarAtividadeNoMolde(AtividadeTemplateRequestDTO dto) {

        // 1. Validação de Negócio: O Perfil pai (ex: Motorista) existe no banco?
        PerfilPassaporte perfilPai = perfilPassaporteRepository.findById(dto.perfilPassaporteId())
                .orElseThrow(() -> new RuntimeException("Perfil de Passaporte não encontrado!"));

        // 2. Instanciação da Entidade que o Hibernate entende
        AtividadeTemplate novaAtividade = new AtividadeTemplate();
        novaAtividade.setNome(dto.nome());
        novaAtividade.setOrdem(dto.ordem());
        novaAtividade.setPerfilResponsavel(dto.perfilResponsavel());

        // AQUI ESTÁ A AMARRAÇÃO: Vinculamos o objeto Perfil inteiro na Atividade
        novaAtividade.setPerfil(perfilPai);

        // 3. Persistência no Banco H2
        atividadeTemplateRepository.save(novaAtividade);
    }
}
