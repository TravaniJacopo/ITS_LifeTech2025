//esercitazione: Sistema Triage Pronto Soccorso -- UF 9.1 Programmazione ad oggetti in Java
//Battistella - Travani

import java.time.LocalDate;
import java.util.ArrayList;

//dati del paziente e sue visite
public class Paziente {

    private String codiceFiscale; //codice fiscale del paziente
    private String nome; //nome
    private String cognome; //cognome
    private ArrayList<Visita> visite; //lista visite fatte
    private LocalDate dataRegistrazione; //data di registrazione

    //costruttore per nuovo paziente
    public Paziente(String codiceFiscale, String nome, String cognome) 
    {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.visite = new ArrayList<>();
        this.dataRegistrazione = LocalDate.now();
    }

    //costruttore usato quando si legge da file
    public Paziente(String codiceFiscale, String nome, String cognome, LocalDate dataRegistrazione) 
    {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.visite = new ArrayList<>();
        this.dataRegistrazione = dataRegistrazione;
    }

    public String getCodiceFiscale() 
    {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) 
    {
        this.codiceFiscale = codiceFiscale;
    }

    public String getNome() 
    {
        return nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public String getCognome() 
    {
        return cognome;
    }

    public void setCognome(String cognome) 
    {
        this.cognome = cognome;
    }

    public ArrayList<Visita> getVisite() 
    {
        return visite;
    }

    public LocalDate getDataRegistrazione() 
    {
        return dataRegistrazione;
    }

    public void setDataRegistrazione(LocalDate dataRegistrazione) 
    {
        this.dataRegistrazione = dataRegistrazione;
    }

    //ritorna COGNOME NOME in maiuscolo
    public String getNomeCompleto() 
    {
        return (cognome + " " + nome).toUpperCase();
    }

    public void aggiungiVisita(Visita v) 
    {
        visite.add(v);
    }

    //calcola la priorità in base a triage e ultima visita
    public String calcolaPriorita(String coloreTriage) 
    {
        if (visite.isEmpty()) 
        {
            return "NON VISITATO";
        }
        Visita ultima = visite.get(visite.size() - 1);
        String colore = coloreTriage != null ? coloreTriage.trim().toLowerCase() : "";

        if ("rosso".equals(colore)) 
        {
            if (ultima instanceof VisitaCardiologica) 
            {
                return "CODICE 1 - EMERGENZA";
            }
            return "CODICE 2 - URGENTE";
        }
        if ("giallo".equals(colore)) 
        {
            return "CODICE 3 - PRIORITARIO";
        }
        if ("verde".equals(colore) || "bianco".equals(colore))
        {
            return "CODICE 4 - STANDARD";
        }
        return "NON DEFINITA";
    }

    public void stampaScheda(String coloreAttuale, ArrayList<Accesso> accessi) 
    {
        String sep  = "╠══════════════════════════════════════════════════╣";
        String sepL = "╠──────────────────────────────────────────────────╣";
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║  SCHEDA PAZIENTE - CLINICA TRIAGE               ║");
        System.out.println(sep);
        System.out.println("║  Codice Fiscale: " + codiceFiscale);
        System.out.println("║  Paziente: " + getNomeCompleto());
        System.out.println("║  Triage attuale: " + (coloreAttuale != null ? coloreAttuale.toUpperCase() : "N/D"));
        System.out.println("║  Registrato il: " + dataRegistrazione);
        System.out.println(sep);

        System.out.println("║  Storico accessi:");
        if (accessi == null || accessi.isEmpty()) 
        {
            System.out.println("║    Nessun accesso registrato");
        } 
        else 
        {
            for (Accesso a : accessi) 
            {
                System.out.println("║    " + a.getData() + " - " + a.getColore().toUpperCase());
            }
        }
        System.out.println(sep);

        if (visite.isEmpty()) 
        {
            System.out.println("║  Nessuna visita registrata");
        } 
        else 
        {
            for (int i = 0; i < visite.size(); i++) 
            {
                Visita v = visite.get(i);
                String tipoVisita;

                if (v instanceof VisitaCardiologica) 
                {
                    tipoVisita = "CARDIOLOGICA";
                } else if (v instanceof VisitaOrtopedica) {
                    tipoVisita = "ORTOPEDICA";
                } else {
                    tipoVisita = "GENERICA";
                }
                System.out.println("║  VISITA " + (i + 1) + " — " + tipoVisita + " " + v.getData());
                System.out.println("║    Medico: " + v.getMedico());
                System.out.println("║    Diagnosi: " + v.getDiagnosi());

                if (v instanceof VisitaCardiologica) 
                {
                    VisitaCardiologica vc = (VisitaCardiologica) v;
                    System.out.println("║    FC: " + vc.getFrequenzaCardiaca()
                            + " bpm | Pressione: " + vc.getPressioneSist() + "/" + vc.getPressioneDiast() + " mmHg");
                } else if (v instanceof VisitaOrtopedica) {
                    VisitaOrtopedica vo = (VisitaOrtopedica) v;
                    System.out.println("║    Parte corpo: " + vo.getParteCorpo());
                } else if (v instanceof VisitaGenerica) {
                    VisitaGenerica vg = (VisitaGenerica) v;
                    System.out.println("║    Reparto: " + vg.getReparto());
                }

                if (i < visite.size() - 1) 
                {
                    System.out.println(sepL);
                }
            }
        }

        System.out.println(sep);
        System.out.println("║  PRIORITÀ: " + calcolaPriorita(coloreAttuale));
        System.out.println("║  Visite totali: " + visite.size());
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
}
