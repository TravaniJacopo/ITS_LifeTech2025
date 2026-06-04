package it.itsvolta.corsobackend2026.regionimanager.dto;

public class RegioniConteggioDto {

    private int numeroRegioni;

    //costruttore vuoto
    public RegioniConteggioDto(){};

    //costruttore
    public RegioniConteggioDto(int numeroRegioni) {
        this.numeroRegioni = numeroRegioni;

    }
    //getter e setter
    public int  getNumeroRegioni()               { return numeroRegioni; }


}
