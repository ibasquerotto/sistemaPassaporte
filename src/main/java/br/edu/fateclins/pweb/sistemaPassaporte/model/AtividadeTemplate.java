package br.edu.fateclins.pweb.sistemaPassaporte.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_atividade_template")
public class AtividadeTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column
    private Integer ordem;

    @Enumerated(EnumType.STRING)
    private PerfilUsuarioEnum perfilResponsavel;

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private PerfilPassaporte perfil;

    public AtividadeTemplate() {
    }

    public AtividadeTemplate(Long id, String nome, Integer ordem, PerfilUsuarioEnum perfilResponsavel) {
        this.id = id;
        this.nome = nome;
        this.ordem = ordem;
        this.perfilResponsavel = perfilResponsavel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer sequencia) {
        this.ordem = sequencia;
    }

    public PerfilUsuarioEnum getPerfilResponsavel() {
        return perfilResponsavel;
    }

    public void setPerfilResponsavel(PerfilUsuarioEnum perfilResponsavel) {
        this.perfilResponsavel = perfilResponsavel;
    }

    public PerfilPassaporte getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilPassaporte perfil) {
        this.perfil = perfil;
    }


}
