//esercitazione: Sistema Triage Pronto Soccorso -- UF 9.1 Programmazione ad oggetti in Java
//Battistella - Travani - Persico

import java.time.LocalDate;

//classe base per una visita medica
public abstract class Visita {

    private LocalDate data; //giorno della visita
    private String medico; //nome del medico
    private String diagnosi; //diagnosi scritta dal medico

    public Visita(LocalDate data, String medico, String diagnosi) 
    {
        this.data = data;
        this.medico = medico;
        this.diagnosi = diagnosi;
    }

    public LocalDate getData() 
    {
        return data;
    }

    public String getMedico() 
    {
        return medico;
    }

    public String getDiagnosi()
    {
        return diagnosi;
    }

    //ogni tipo di visita deve dire come si descrive
    public abstract String descriviVisita();
}
