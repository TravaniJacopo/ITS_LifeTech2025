package it.itsvolta.corsobackend2026.regionimanager.dto;


public class RegioneDto {
    
    private String codice;
    private String nome;

    //Costruttore vuote
    public RegioneDto (){};

    //costruttore
    public RegioneDto(String codice, String nome) {
        this.codice = codice;
        this.nome = nome;

    }
    //getter e setter
    public String getNome()               { return nome; }
    public void   setNome(String nome)   { this.nome = nome; }

    public String getCodice()                { return codice; }
    public void   setCodice (String codice)     { this.codice = codice; }

}
    

