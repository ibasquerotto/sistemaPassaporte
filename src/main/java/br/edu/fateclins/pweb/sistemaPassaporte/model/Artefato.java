package br.edu.fateclins.pweb.sistemaPassaporte.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_artefatos")

public class Artefato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nomeArquivo;

    @Column(nullable = false, length = 100)
    private String caminhoUrl;

    @Column(nullable = false, length = 100)
    private String tokenAssinatura;

    @ManyToOne
    @JoinColumn(name = "tarefa_id", nullable = true)
    private Tarefa tarefa;

    @ManyToOne
    @JoinColumn(name = "subtarefa_id", nullable = true)
    private Subtarefa subtarefa;

    public Artefato() {
    }

    public Artefato(Long id, String nomeArquivo, String caminhoUrl, String tokenAssinatura, Tarefa tarefa, Subtarefa subtarefa) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.caminhoUrl = caminhoUrl;
        this.tokenAssinatura = tokenAssinatura;
        this.tarefa = tarefa;
        this.subtarefa = subtarefa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getCaminhoUrl() {
        return caminhoUrl;
    }

    public void setCaminhoUrl(String caminhoUrl) {
        this.caminhoUrl = caminhoUrl;
    }

    public String getTokenAssinatura() {
        return tokenAssinatura;
    }

    public void setTokenAssinatura(String tokenAssinatura) {
        this.tokenAssinatura = tokenAssinatura;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public Subtarefa getSubtarefa() {
        return subtarefa;
    }

    public void setSubtarefa(Subtarefa subtarefa) {
        this.subtarefa = subtarefa;
    }
}
