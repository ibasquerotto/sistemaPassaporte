package br.edu.fateclins.pweb.sistemaPassaporte.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_usuarios")

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 8)
    private String senha;

    @Enumerated(EnumType.STRING) //retorna o nome do perfil e não o seu ID
    private PerfilUsuarioEnum perfil;

    @Column(unique = true)
    private String chaveAcesso;

    public Usuario() {
    }

    public Usuario(int id, String nome, String email, String senha, PerfilUsuarioEnum perfil, String chaveAcesso) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        this.chaveAcesso = chaveAcesso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PerfilUsuarioEnum getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuarioEnum perfil) {
        this.perfil = perfil;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuario usuario)) return false;
        return id == usuario.id && Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha) && perfil == usuario.perfil && Objects.equals(chaveAcesso, usuario.chaveAcesso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, senha, perfil, chaveAcesso);
    }
}
