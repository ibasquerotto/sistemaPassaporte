package br.edu.fateclins.pweb.sistemaPassaporte.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_perfil_passaporte")

public class PerfilPassaporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false)
    private Integer versao;

    @Column
    private boolean estaAtivo;

    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL)
    private List<AtividadeTemplate> atividades;

    public PerfilPassaporte() {
    }

    public PerfilPassaporte(Long id, String nome, Integer versao, boolean estaAtivo) {
        this.id = id;
        this.nome = nome;
        this.versao = versao;
        this.estaAtivo = estaAtivo;
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

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }

    public boolean isEstaAtivo() {
        return estaAtivo;
    }

    public void setEstaAtivo(boolean estaAtivo) {
        this.estaAtivo = estaAtivo;
    }

    public List<AtividadeTemplate> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<AtividadeTemplate> atividades) {
        this.atividades = atividades;
    }
}
