//esercitazione: Sistema Triage Pronto Soccorso -- UF 9.1 Programmazione ad oggetti in Java
//Battistella - Travani

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//gestisce i pazienti registrati in clinica
public class Clinica {

    private final HashMap<String, Paziente> archivio; //archivio per codice fiscale
    private final ArrayList<Accesso> accessi; //storico accessi triage

    //crea una clinica con archivio vuoto
    public Clinica() 
    {
        this.archivio = new HashMap<>();
        this.accessi = new ArrayList<>();
    }

    public HashMap<String, Paziente> getArchivio() 
    {
        return archivio;
    }

    public ArrayList<Accesso> getAccessi() 
    {
        return accessi;
    }

    //registra il paziente in archivio
    public void registraPaziente(Paziente p) 
    {
        archivio.put(p.getCodiceFiscale(), p);
    }

    public void aggiungiAccesso(Accesso accesso) 
    {
        accessi.add(accesso);
    }

    //cerca un paziente tramite codice fiscale
    public Paziente cercaPaziente(String cf) throws PazienteNonTrovatoException 
    {
        Paziente p = archivio.get(cf);
        if (p == null) 
        {
            throw new PazienteNonTrovatoException("Paziente con codice fiscale " + cf + " non trovato.");
        }
        return p;
    }

    //ritorna i pazienti con il colore triage richiesto
    public List<Paziente> getPazientiPerColore(String colore) 
    {
        List<Paziente> risultato = new ArrayList<>();
        for (Paziente p : archivio.values()) 
        {
            String coloreAttuale = getColoreAttuale(p.getCodiceFiscale());
            if (coloreAttuale != null && coloreAttuale.equalsIgnoreCase(colore)) 
            {
                risultato.add(p);
            }
        }
        return risultato;
    }

    public List<Accesso> getAccessiPaziente(String cf) 
    {
        List<Accesso> risultato = new ArrayList<>();
        for (Accesso a : accessi) 
        {
            if (a.getCf().equalsIgnoreCase(cf)) 
            {
                risultato.add(a);
            }
        }
        return risultato;
    }

    public String getColoreAttuale(String cf) 
    {
        Accesso ultimo = null;
        for (Accesso a : accessi) 
        {
            if (a.getCf().equalsIgnoreCase(cf)) 
            {
                if (ultimo == null) 
                {
                    ultimo = a;
                } 
                else if (a.getData().isAfter(ultimo.getData())) 
                {
                    ultimo = a;
                }
            }
        }
        return ultimo != null ? ultimo.getColore() : null;
    }

    //stampa statistiche generali della clinica
    public void stampaStatistiche() 
    {
        System.out.println("\n========== STATISTICHE CLINICA ==========");
        System.out.println("Totale pazienti registrati: " + archivio.size());

        int rosso = 0;
        int giallo = 0;
        int verde = 0;
        int bianco = 0;
        int altro = 0;
        int totVisite = 0;
        int totAccessi = accessi.size();

        for (Paziente p : archivio.values()) 
        {
            totVisite += p.getVisite().size();
            String coloreAttuale = getColoreAttuale(p.getCodiceFiscale());
            String c = coloreAttuale == null ? "" : coloreAttuale.trim().toLowerCase();
            switch (c) 
            {
                case "rosso":
                    rosso++;
                    break;
                case "giallo":
                    giallo++;
                    break;
                case "verde":
                    verde++;
                    break;
                case "bianco":
                    bianco++;
                    break;
                default:
                    altro++;
                    break;
            }
        }

        System.out.println("Distribuzione triage:");
        System.out.println("  Rosso:  " + rosso);
        System.out.println("  Giallo: " + giallo);
        System.out.println("  Verde:  " + verde);
        System.out.println("  Bianco: " + bianco);

        if (altro > 0) 
        {
            System.out.println("  Altro/non classificato: " + altro);
        }
        System.out.println("Totale visite (somma su tutti i pazienti): " + totVisite);
        System.out.println("Totale accessi triage registrati: " + totAccessi);
        if (!archivio.isEmpty()) 
        {
            double media = (double) totVisite / archivio.size();
            System.out.println("Media visite per paziente: " + Math.round(media * 10.0) / 10.0);
        }
        System.out.println("==========================================\n");
    }

    //sostituisce l'archivio con quello letto da file
    public void ripristinaArchivio(Map<String, Paziente> nuovo, List<Accesso> accessiLetti) 
    {
        archivio.clear();
        archivio.putAll(nuovo);
        accessi.clear();
        if (accessiLetti != null) 
        {
            accessi.addAll(accessiLetti);
        }
    }
}
