//esercitazione: Sistema Triage Pronto Soccorso -- UF 9.1 Programmazione ad oggetti in Java
//Battistella - Travani

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//legge e salva l'archivio pazienti su file
public class GestoreArchivio {

    private final ArrayList<Accesso> accessiCaricati = new ArrayList<>();

    public ArrayList<Accesso> getAccessiCaricati() 
    {
        return accessiCaricati;
    }

    //salva tutto l'archivio su file di testo
    public void salvaArchivio(HashMap<String, Paziente> archivio, List<Accesso> accessi, String nomeFile) throws IOException 
    {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(nomeFile)))) 
        {
            for (Paziente p : archivio.values()) 
            {
                pw.println("### PAZIENTE");
                pw.println("CF:" + p.getCodiceFiscale());
                pw.println("NOME:" + p.getNome());
                pw.println("COGNOME:" + p.getCognome());
                pw.println("DATA_REG:" + p.getDataRegistrazione().toString());

                if (accessi != null) 
                {
                    for (Accesso a : accessi) 
                    {
                        if (a.getCf().equalsIgnoreCase(p.getCodiceFiscale())) 
                        {
                            pw.println("--- ACCESSO");
                            pw.println("DATA:" + a.getData().toString());
                            pw.println("COLORE:" + a.getColore());
                            pw.println("--- FINE_ACCESSO");
                        }
                    }
                }

                for (Visita v : p.getVisite()) 
                {
                    if (v instanceof VisitaCardiologica) 
                    {
                        VisitaCardiologica vc = (VisitaCardiologica) v;
                        pw.println("--- VISITA:Cardiologica");
                        pw.println("DATA:" + v.getData().toString());
                        pw.println("MEDICO:" + v.getMedico());
                        pw.println("DIAGNOSI:" + v.getDiagnosi());
                        pw.println("FREQUENZA:" + vc.getFrequenzaCardiaca());
                        pw.println("PRESSIONE_SIST:" + vc.getPressioneSist());
                        pw.println("PRESSIONE_DIAST:" + vc.getPressioneDiast());
                    } else if (v instanceof VisitaOrtopedica) {
                        VisitaOrtopedica vo = (VisitaOrtopedica) v;
                        pw.println("--- VISITA:Ortopedica");
                        pw.println("DATA:" + v.getData().toString());
                        pw.println("MEDICO:" + v.getMedico());
                        pw.println("DIAGNOSI:" + v.getDiagnosi());
                        pw.println("PARTE_CORPO:" + vo.getParteCorpo());
                    } else if (v instanceof VisitaGenerica) {
                        VisitaGenerica vg = (VisitaGenerica) v;
                        pw.println("--- VISITA:Generica");
                        pw.println("DATA:" + v.getData().toString());
                        pw.println("MEDICO:" + v.getMedico());
                        pw.println("DIAGNOSI:" + v.getDiagnosi());
                        pw.println("REPARTO:" + vg.getReparto());
                    }
                    pw.println("--- FINE_VISITA");
                }
                pw.println("### FINE_PAZIENTE");
            }
        }
    }

    //carica tutto l'archivio da file di testo
    public HashMap<String, Paziente> caricaArchivio(String nomeFile) throws IOException 
    {
        HashMap<String, Paziente> map = new HashMap<>();
        accessiCaricati.clear();
        Paziente corrente = null;

        String tipoVisita = null;
        LocalDate dataVisita = null;
        String medicoVisita = null;
        String diagnosiVisita = null;
        String repartoVisita = null;
        String parteCorpoVisita = null;
        int frequenzaVisita = 0;
        int sistVisita = 0;
        int diastVisita = 0;

        boolean inAccesso = false;
        LocalDate dataAccesso = null;
        String coloreAccesso = null;

        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.equals("### PAZIENTE")) 
                {
                    corrente = new Paziente("", "", "", LocalDate.now());
                } else if (line.equals("### FINE_PAZIENTE")) {
                    //paziente gia' aggiunto alla map quando abbiamo letto DATA_REG
                } else if (line.startsWith("--- VISITA:")) {
                    tipoVisita = line.substring("--- VISITA:".length()).trim();
                    dataVisita = null; medicoVisita = null; diagnosiVisita = null;
                    repartoVisita = null; parteCorpoVisita = null;
                    frequenzaVisita = 0; sistVisita = 0; diastVisita = 0;
                } else if (line.equals("--- ACCESSO")) {
                    inAccesso = true;
                    dataAccesso = null;
                    coloreAccesso = null;
                } else if (line.equals("--- FINE_ACCESSO")) {
                    if (corrente != null && dataAccesso != null && coloreAccesso != null) 
                    {
                        accessiCaricati.add(new Accesso(corrente.getCodiceFiscale(), coloreAccesso, dataAccesso));
                    }
                    inAccesso = false;
                } else if (line.equals("--- FINE_VISITA")) {
                    if (corrente != null && tipoVisita != null) 
                    {
                        switch (tipoVisita) 
                        {
                            case "Cardiologica":
                                corrente.aggiungiVisita(new VisitaCardiologica(
                                        dataVisita, medicoVisita, diagnosiVisita,
                                        frequenzaVisita, sistVisita, diastVisita));
                                break;
                            case "Ortopedica":
                                corrente.aggiungiVisita(new VisitaOrtopedica(
                                        dataVisita, medicoVisita, diagnosiVisita, parteCorpoVisita));
                                break;
                            case "Generica":
                                corrente.aggiungiVisita(new VisitaGenerica(
                                        dataVisita, medicoVisita, diagnosiVisita, repartoVisita));
                                break;
                            default:
                                throw new IOException("Tipo visita non riconosciuto: " + tipoVisita);
                        }
                    }
                    tipoVisita = null;
                } else if (line.contains(":")) {
                    String[] parti = line.split(":", 2);
                    if (parti.length < 2) 
                    {
                        continue;
                    }
                    String chiave = parti[0].trim();
                    String valore = parti[1].trim();

                    if (inAccesso) 
                    {
                        switch (chiave) 
                        {
                            case "DATA":   dataAccesso = LocalDate.parse(valore); break;
                            case "COLORE": coloreAccesso = valore; break;
                        }
                    }
                    else if (tipoVisita == null) 
                    {
                        switch (chiave) 
                        {
                            case "CF":       if (corrente != null) corrente.setCodiceFiscale(valore); break;
                            case "NOME":     if (corrente != null) corrente.setNome(valore); break;
                            case "COGNOME":  if (corrente != null) corrente.setCognome(valore); break;
                            case "DATA_REG":
                                if (corrente != null) 
                                {
                                    corrente.setDataRegistrazione(LocalDate.parse(valore));
                                    map.put(corrente.getCodiceFiscale(), corrente);
                                }
                                break;
                        }
                    } 
                    else 
                    {
                        switch (chiave) 
                        {
                            case "DATA":            dataVisita = LocalDate.parse(valore); break;
                            case "MEDICO":          medicoVisita = valore; break;
                            case "DIAGNOSI":        diagnosiVisita = valore; break;
                            case "REPARTO":         repartoVisita = valore; break;
                            case "PARTE_CORPO":     parteCorpoVisita = valore; break;
                            case "FREQUENZA":       frequenzaVisita = Integer.parseInt(valore); break;
                            case "PRESSIONE_SIST":  sistVisita = Integer.parseInt(valore); break;
                            case "PRESSIONE_DIAST": diastVisita = Integer.parseInt(valore); break;
                        }
                    }
                }
            }
        }
        return map;
    }
}