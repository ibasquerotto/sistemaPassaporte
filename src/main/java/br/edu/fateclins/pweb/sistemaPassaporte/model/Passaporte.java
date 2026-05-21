package br.edu.fateclins.pweb.sistemaPassaporte.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_passaportes")

public class Passaporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate dataCriacao;

    @Column
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private Usuario candidato;

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private PerfilPassaporte perfilModelo;

    public Passaporte() {
    }

    public Passaporte(Long id, LocalDate dataCriacao, StatusEnum status, Usuario candidato, PerfilPassaporte perfilModelo) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.candidato = candidato;
        this.perfilModelo = perfilModelo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Usuario getCandidato() {
        return candidato;
    }

    public void setCandidato(Usuario candidato) {
        this.candidato = candidato;
    }

    public PerfilPassaporte getPerfilModelo() {
        return perfilModelo;
    }

    public void setPerfilModelo(PerfilPassaporte perfilModelo) {
        this.perfilModelo = perfilModelo;
    }
}
