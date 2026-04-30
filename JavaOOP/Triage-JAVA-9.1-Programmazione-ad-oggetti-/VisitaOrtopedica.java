//esercitazione: Sistema Triage Pronto Soccorso -- UF 9.1 Programmazione ad oggetti in Java
//Battistella - Travani - Persico

import java.time.LocalDate;

//visita ortopedica con parte del corpo
public class VisitaOrtopedica extends Visita {

    private String parteCorpo; //parte del corpo controllata

    //costruttore
    public VisitaOrtopedica(LocalDate data, String medico, String diagnosi, String parteCorpo) 
    {
        super(data, medico, diagnosi);
        this.parteCorpo = parteCorpo;
    }

    public String getParteCorpo() 
    {
        return parteCorpo;
    }

    @Override
    public String descriviVisita() 
    {
        return "Visita ortopedica | Parte del corpo: " + parteCorpo
                + " | Medico: " + getMedico()
                + " | Data: " + getData()
                + " | Diagnosi: " + getDiagnosi();
    }
}
