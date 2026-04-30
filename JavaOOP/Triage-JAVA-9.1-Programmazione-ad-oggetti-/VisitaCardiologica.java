//esercitazione: Sistema Triage Pronto Soccorso -- UF 9.1 Programmazione ad oggetti in Java
//Battistella - Travani - Persico

import java.time.LocalDate;

//visita cardiologica con parametri vitali
public class VisitaCardiologica extends Visita {
    private int frequenzaCardiaca; //battiti per minuto
    private int pressioneSist; //pressione massima
    private int pressioneDiast; //pressione minima

    //costruttore
    public VisitaCardiologica(LocalDate data, String medico, String diagnosi, int frequenzaCardiaca, int pressioneSist, int pressioneDiast) 
    {
        super(data, medico, diagnosi);
        this.frequenzaCardiaca = frequenzaCardiaca;
        this.pressioneSist = pressioneSist;
        this.pressioneDiast = pressioneDiast;
    }

    public int getFrequenzaCardiaca() 
    {
        return frequenzaCardiaca;
    }

    public int getPressioneSist() 
    {
        return pressioneSist;
    }

    public int getPressioneDiast() 
    {
        return pressioneDiast;
    }

    @Override
    public String descriviVisita() 
    {
        return "Visita cardiologica | FC: " + frequenzaCardiaca + " bpm"
                + " | PA: " + pressioneSist + "/" + pressioneDiast
                + " | Medico: " + getMedico()
                + " | Data: " + getData()
                + " | Diagnosi: " + getDiagnosi();
    }
}
