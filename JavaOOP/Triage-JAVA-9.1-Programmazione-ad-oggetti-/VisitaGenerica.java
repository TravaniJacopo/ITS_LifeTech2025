//esercitazione: Sistema Triage Pronto Soccorso -- UF 9.1 Programmazione ad oggetti in Java
//Battistella - Travani - Persico

import java.time.LocalDate;

//visita generica con reparto
public class VisitaGenerica extends Visita {

    private String reparto; //reparto della visita

    //costruttore
    public VisitaGenerica(LocalDate data, String medico, String diagnosi, String reparto) 
    {
        super(data, medico, diagnosi);
        this.reparto = reparto;
    }

    public String getReparto() 
    {
        return reparto;
    }

    @Override
    public String descriviVisita() 
    {
        return "Visita generica | Reparto: " + reparto
                + " | Medico: " + getMedico()
                + " | Data: " + getData()
                + " | Diagnosi: " + getDiagnosi();
    }
}
