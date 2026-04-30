//esercitazione: Sistema Triage Pronto Soccorso -- UF 9.1 Programmazione ad oggetti in Java
//Battistella - Travani

import java.time.LocalDate;

public class Accesso {

    private String cf;
    private String colore;
    private LocalDate data;

    public Accesso(String cf, String colore, LocalDate data) {
        this.cf = cf;
        this.colore = colore;
        this.data = data;
    }

    public String getCf() {
        return cf;
    }

    public String getColore() {
        return colore;
    }

    public LocalDate getData() {
        return data;
    }
}