package br.edu.fateclins.pweb.sistemaPassaporte.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_tarefas")

public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column
    private LocalDate prazo;

    @ManyToOne
    @JoinColumn(name = "atividade_id")
    private Atividade atividade;


    public Tarefa() {
    }

    public Tarefa(Long id, String nome, StatusEnum status, LocalDate prazo, Atividade atividade) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.prazo = prazo;
        this.atividade = atividade;
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }
}
