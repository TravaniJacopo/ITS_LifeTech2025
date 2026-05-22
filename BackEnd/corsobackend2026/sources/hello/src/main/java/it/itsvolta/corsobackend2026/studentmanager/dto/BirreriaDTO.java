package it.itsvolta.corsobackend2026.studentmanager.dto;

public class BirreriaDTO {
    
    private String nome;
    private String country;


    public BirreriaDTO() {
    }

    public BirreriaDTO(String nome, String country) {
        this.nome = nome;
        this.country = country;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
