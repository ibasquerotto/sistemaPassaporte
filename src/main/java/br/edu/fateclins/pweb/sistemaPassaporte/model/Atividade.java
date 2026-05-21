package br.edu.fateclins.pweb.sistemaPassaporte.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_atividades")

public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario responsavelEspecifico;

    @ManyToOne
    private Passaporte passaporte;

    public Atividade() {

    }

    public Atividade(Long id, String nome, StatusEnum status, Usuario responsavelEspecifico, Passaporte passaporte) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.responsavelEspecifico = responsavelEspecifico;
        this.passaporte = passaporte;
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

    public Usuario getResponsavelEspecifico() {
        return responsavelEspecifico;
    }

    public void setResponsavelEspecifico(Usuario responsavelEspecifico) {
        this.responsavelEspecifico = responsavelEspecifico;
    }

    public Passaporte getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(Passaporte passaporte) {
        this.passaporte = passaporte;
    }
}
