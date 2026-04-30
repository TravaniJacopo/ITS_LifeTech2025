//esercitazione: Sistema Triage Pronto Soccorso -- UF 9.1 Programmazione ad oggetti in Java
//Battistella - Travani

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Clinica clinica = new Clinica();
        GestoreArchivio gestore = new GestoreArchivio();

        //caricamento automatico all'avvio
        try 
        {
            clinica.ripristinaArchivio(gestore.caricaArchivio("pazienti.txt"), gestore.getAccessiCaricati());
            System.out.println("Archivio caricato: " + clinica.getArchivio().size() + " paziente/i trovati.");
        } 
        catch (IOException e) 
        {
            System.out.println("Nessun archivio esistente trovato. Partenza con archivio vuoto.");
        }

        try (Scanner scanner = new Scanner(System.in)) 
        {
            boolean continua = true;

            while (continua) 
            {
                stampaMenu();
                String sceltaLine = scanner.nextLine().trim();
                int scelta;

                try 
                {
                    scelta = Integer.parseInt(sceltaLine);
                } 
                catch (NumberFormatException e) 
                {
                    System.out.println("Inserire un numero intero valido.");
                    continue;
                }

                try 
                {
                    switch (scelta) 
                    {
                        case 1:
                            registraPaziente(clinica, scanner);
                            break;
                        case 2:
                            cercaPazienteMenu(clinica, scanner);
                            break;
                        case 3:
                            aggiungiAccesso(clinica, scanner);
                            break;
                        case 4:
                            aggiungiVisitaMenu(clinica, scanner);
                            break;
                        case 5:
                            stampaSchedaMenu(clinica, scanner);
                            break;
                        case 6:
                            elencaPerColore(clinica, scanner);
                            break;
                        case 7:
                            clinica.stampaStatistiche();
                            break;
                        case 8:
                            salvaSuFile(clinica, gestore, scanner);
                            break;
                        case 9:
                            caricaDaFile(clinica, gestore, scanner);
                            break;
                        case 10:
                            continua = false;
                            try 
                            {
                                gestore.salvaArchivio(clinica.getArchivio(), clinica.getAccessi(), "pazienti.txt");
                                System.out.println("Archivio salvato automaticamente.");
                            } 
                            catch (IOException e) 
                            {
                                System.out.println("[File] Salvataggio automatico fallito: " + e.getMessage());
                            }
                            System.out.println("Uscita dal programma. Arrivederci.");
                            break;
                        default:
                            System.out.println("Opzione non valida.");
                    }
                } catch (CodiceFiscaleNonValidoException e) {
                    System.out.println("[Errore CF] " + e.getMessage());
                } catch (PazienteNonTrovatoException e) {
                    System.out.println("[Errore] " + e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("[Numero] Inserire un valore numerico valido.");
                } catch (IllegalArgumentException e) {
                    System.out.println("[Validazione] " + e.getMessage());
                } catch (DateTimeParseException e) {
                    System.out.println("[Data] Formato non valido. Usare yyyy-MM-dd (es. 2026-03-28).");
                } catch (IOException e) {
                    System.out.println("[File] " + e.getMessage());
                }
            }
        }
    }

    //stampa le opzioni del menu principale
    private static void stampaMenu() 
    {
        System.out.println("\n--- SISTEMA CLINICA TRIAGE 2.0 ---");
        System.out.println("1. Registra paziente");
        System.out.println("2. Cerca paziente");
        System.out.println("3. Aggiungi accesso in pronto soccorso / cambia colore");
        System.out.println("4. Aggiungi visita");
        System.out.println("5. Stampa scheda");
        System.out.println("6. Elenca pazienti per colore triage");
        System.out.println("7. Statistiche");
        System.out.println("8. Salva su file");
        System.out.println("9. Carica da file");
        System.out.println("10. Esci");
        System.out.print("Scelta: ");
    }

    //registra un nuovo paziente con controlli base
    private static void registraPaziente(Clinica clinica, Scanner sc) throws CodiceFiscaleNonValidoException 
    {
        System.out.print("Codice fiscale (16 caratteri): ");
        String cf = leggiCodiceFiscale(sc);
        if (clinica.getArchivio().containsKey(cf)) 
        {
            System.out.println("Attenzione: esiste già un paziente con questo CF. Registrazione annullata.");
            return;
        }
        System.out.print("Nome: ");
        String nome = leggiNonVuoto(sc, "Nome");
        System.out.print("Cognome: ");
        String cognome = leggiNonVuoto(sc, "Cognome");
        System.out.print("Colore triage (Rosso/Giallo/Verde/Bianco): ");
        String colore = leggiColoreTriage(sc);
        System.out.print("Data accesso (yyyy-MM-dd): ");
        LocalDate dataAccesso = LocalDate.parse(sc.nextLine().trim());

        Paziente p = new Paziente(cf, nome, cognome);
        clinica.registraPaziente(p);
        clinica.aggiungiAccesso(new Accesso(cf, colore, dataAccesso));
        System.out.println("Paziente registrato correttamente.");
    }

    //cerca un paziente e stampa il nome completo
    private static void cercaPazienteMenu(Clinica clinica, Scanner sc) throws PazienteNonTrovatoException, CodiceFiscaleNonValidoException 
    {
        System.out.print("Codice fiscale: ");
        String cf = leggiCodiceFiscale(sc);
        Paziente p = clinica.cercaPaziente(cf);
        System.out.println("Paziente trovato: " + p.getNomeCompleto());
    }

    //stampa la scheda completa del paziente
    private static void stampaSchedaMenu(Clinica clinica, Scanner sc) throws PazienteNonTrovatoException, CodiceFiscaleNonValidoException 
    {
        System.out.print("Codice fiscale: ");
        String cf = leggiCodiceFiscale(sc);
        Paziente p = clinica.cercaPaziente(cf);
        String coloreAttuale = clinica.getColoreAttuale(cf);
        p.stampaScheda(coloreAttuale, new java.util.ArrayList<>(clinica.getAccessiPaziente(cf)));
    }

    //aggiunge una nuova visita al paziente scelto
    private static void aggiungiVisitaMenu(Clinica clinica, Scanner sc) throws PazienteNonTrovatoException, CodiceFiscaleNonValidoException 
    {
        System.out.print("Codice fiscale del paziente: ");
        String cf = leggiCodiceFiscale(sc);
        Paziente p = clinica.cercaPaziente(cf);

        System.out.println("Tipo visita: 1=Generica, 2=Ortopedica, 3=Cardiologica");
        System.out.print("Scelta: ");
        String tipo = sc.nextLine().trim();

        System.out.print("Data visita (yyyy-MM-dd): ");
        LocalDate data = LocalDate.parse(sc.nextLine().trim());
        System.out.print("Medico: ");
        String medico = leggiNonVuoto(sc, "Medico");
        System.out.print("Diagnosi: ");
        String diagnosi = leggiNonVuoto(sc, "Diagnosi");

        switch (tipo) 
        {
            case "1":
                System.out.print("Reparto: ");
                String reparto = leggiNonVuoto(sc, "Reparto");
                p.aggiungiVisita(new VisitaGenerica(data, medico, diagnosi, reparto));
                break;
            case "2":
                System.out.print("Parte del corpo: ");
                String parte = leggiNonVuoto(sc, "Parte del corpo");
                p.aggiungiVisita(new VisitaOrtopedica(data, medico, diagnosi, parte));
                break;
            case "3":
                System.out.print("Frequenza cardiaca (bpm): ");
                int fc = Integer.parseInt(sc.nextLine().trim());
                System.out.print("Pressione sistolica: ");
                int sist = Integer.parseInt(sc.nextLine().trim());
                System.out.print("Pressione diastolica: ");
                int diast = Integer.parseInt(sc.nextLine().trim());
                p.aggiungiVisita(new VisitaCardiologica(data, medico, diagnosi, fc, sist, diast));
                break;
            default:
                throw new IllegalArgumentException("Tipo visita non valido.");
        }
        System.out.println("Visita aggiunta correttamente.");
    }

    //elenca i pazienti per colore triage
    private static void elencaPerColore(Clinica clinica, Scanner sc)
    {
        System.out.print("Colore triage (Rosso/Giallo/Verde/Bianco): ");
        String colore = leggiColoreTriage(sc);
        List<Paziente> lista = clinica.getPazientiPerColore(colore);

        if (lista.isEmpty()) 
        {
            System.out.println("Nessun paziente con colore " + colore + ".");
            return;
        }

        System.out.println("\nPazienti con triage " + colore + ":");
        for (Paziente p : lista) 
        {
            String coloreAttuale = clinica.getColoreAttuale(p.getCodiceFiscale());
            System.out.println(" - " + p.getNomeCompleto() + " | CF: " + p.getCodiceFiscale() + " | Priorità: " + p.calcolaPriorita(coloreAttuale));
        }
    }

    //aggiunge un accesso e aggiorna il colore triage
    private static void aggiungiAccesso(Clinica clinica, Scanner sc) throws PazienteNonTrovatoException, CodiceFiscaleNonValidoException 
    {
        System.out.print("Codice fiscale del paziente: ");
        String cf = leggiCodiceFiscale(sc);
        clinica.cercaPaziente(cf);

        System.out.print("Colore triage (Rosso/Giallo/Verde/Bianco): ");
        String colore = leggiColoreTriage(sc);
        System.out.print("Data accesso (yyyy-MM-dd): ");
        LocalDate data = LocalDate.parse(sc.nextLine().trim());

        clinica.aggiungiAccesso(new Accesso(cf, colore, data));
        System.out.println("Accesso registrato correttamente.");
    }

    //salva l'archivio su file
    private static void salvaSuFile(Clinica clinica, GestoreArchivio gestore, Scanner sc) throws IOException 
    {
        System.out.print("Percorso/nome file (es. archivio.txt): ");
        String nome = sc.nextLine().trim();
        if (nome.isEmpty()) 
        {
            throw new IllegalArgumentException("Nome file non valido.");
        }
        gestore.salvaArchivio(clinica.getArchivio(), clinica.getAccessi(), nome);
        System.out.println("Archivio salvato.");
    }

    //carica un archivio da file
    private static void caricaDaFile(Clinica clinica, GestoreArchivio gestore, Scanner sc) throws IOException 
    {
        System.out.print("Percorso/nome file da caricare: ");
        String nf = sc.nextLine().trim();
        if (nf.isEmpty()) 
        {
            throw new IllegalArgumentException("Nome file non valido.");
        }
        clinica.ripristinaArchivio(gestore.caricaArchivio(nf), gestore.getAccessiCaricati());
        System.out.println("Archivio caricato. Pazienti in anagrafica: " + clinica.getArchivio().size());
    }

    //controlla e sistema il codice fiscale letto da input
    private static String leggiCodiceFiscale(Scanner sc) throws CodiceFiscaleNonValidoException 
    {
        String cf = sc.nextLine().trim();
        if (cf.isEmpty()) 
        {
            throw new CodiceFiscaleNonValidoException("Il codice fiscale non può essere vuoto.");
        }
        if (cf.length() != 16) 
        {
            throw new CodiceFiscaleNonValidoException("Il codice fiscale deve essere lungo esattamente 16 caratteri.");
        }
        return cf.toUpperCase();
    }

    //legge un testo obbligatorio non vuoto
    private static String leggiNonVuoto(Scanner sc, String nomeCampo) 
    {
        String s = sc.nextLine().trim();
        if (s.isEmpty()) 
        {
            throw new IllegalArgumentException(nomeCampo + " non può essere vuoto.");
        }
        return s;
    }

    //controlla e sistema il colore triage
    private static String leggiColoreTriage(Scanner sc) 
    {
        String c = sc.nextLine().trim();
        if (c.isEmpty()) 
        {
            throw new IllegalArgumentException("Il colore triage non può essere vuoto.");
        }
        String norm = c.toLowerCase();
        if (!norm.equals("rosso") && !norm.equals("giallo") && !norm.equals("verde") && !norm.equals("bianco")) 
        {
            throw new IllegalArgumentException("Colore triage non valido. Valori ammessi: Rosso, Giallo, Verde, Bianco.");
        }
        return norm.substring(0, 1).toUpperCase() + norm.substring(1);
    }
}
