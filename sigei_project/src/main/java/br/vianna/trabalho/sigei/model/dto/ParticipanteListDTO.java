package br.vianna.trabalho.sigei.model.dto;

public class ParticipanteListDTO {

    private int id;
    private String nome, email;

    public ParticipanteListDTO(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public ParticipanteListDTO() {
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
}
