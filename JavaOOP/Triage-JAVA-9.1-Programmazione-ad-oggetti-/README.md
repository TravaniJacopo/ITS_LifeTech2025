# Clinica Triage 2.0 🏥

Sistema di gestione triage per un pronto soccorso ospedaliero, sviluppato in **Java** con paradigma **orientato agli oggetti**.

**Autori:** Battistella · Travani · Persico
**Periodo:** 23 marzo 2026 – 30 marzo 2026
**Corso:** UF 9.1 — Programmazione ad Oggetti in Java
**Istituto:** ITS LifeTech Academy, Trieste

---

## Obiettivo

Realizzare un sistema console per la gestione del triage di una clinica ospedaliera. Il sistema mantiene un archivio di pazienti, ciascuno con una storia di visite mediche di diverso tipo. L'archivio è **persistente**: viene caricato da file all'avvio del programma e salvato automaticamente alla chiusura.

---

## Tecnologie e concetti utilizzati

| Ambito | Dettaglio |
|---|---|
| **Linguaggio** | Java (JDK 17+) |
| **Paradigma** | Programmazione orientata agli oggetti (OOP) |
| **Ereditarietà** | Classe astratta `Visita` con tre sottoclassi specializzate |
| **Polimorfismo** | Metodo astratto `descriviVisita()`, `instanceof` per la stampa differenziata |
| **Incapsulamento** | Tutti i campi `private` con getter/setter |
| **Eccezioni custom** | `CodiceFiscaleNonValidoException`, `PazienteNonTrovatoException` |
| **Collezioni** | `HashMap<String, Paziente>` per l'archivio, `ArrayList<Visita>` per le visite |
| **File I/O** | `BufferedReader`, `BufferedWriter`, `PrintWriter`, `FileReader`, `FileWriter` |
| **Date** | `java.time.LocalDate` per date di registrazione e visite |

---

## Struttura del progetto

```
Triage-JAVA-9.1/
│
├── Main.java                          # Entry point: menu console e gestione input
├── Clinica.java                       # Gestione archivio pazienti (HashMap)
├── Paziente.java                      # Dati anagrafici, lista visite, scheda, priorità
├── Accesso.java                       # Storico accessi triage (CF, colore, data)
│
├── Visita.java                        # Classe astratta base per le visite
├── VisitaGenerica.java                # Visita con reparto
├── VisitaOrtopedica.java              # Visita con parte del corpo
├── VisitaCardiologica.java            # Visita con FC e pressione arteriosa
│
├── GestoreArchivio.java               # Salvataggio e caricamento da file di testo
│
├── CodiceFiscaleNonValidoException.java  # Eccezione custom per CF non valido
├── PazienteNonTrovatoException.java      # Eccezione custom per paziente assente
│
├── esercitazione_triage_2marzo.pdf    # Traccia originale (versione 1)
└── esercitazione_triage_v2.pdf        # Traccia aggiornata (versione finale)
```

---

## Architettura OOP

### Gerarchia delle visite

```
Visita  (abstract)
│   - data: LocalDate
│   - medico: String
│   - diagnosi: String
│   + descriviVisita(): String  ← abstract
│
├── VisitaGenerica
│       + reparto: String
│
├── VisitaOrtopedica
│       + parteCorpo: String
│
└── VisitaCardiologica
        + frequenzaCardiaca: int
        + pressioneSist: int
        + pressioneDiast: int
```

### Classi principali

**`Paziente`**
Contiene i dati anagrafici (CF, nome, cognome, data registrazione) e la lista delle visite. Espone i metodi:
- `aggiungiVisita(Visita v)` — aggiunge una visita alla lista
- `calcolaPriorita(colore)` — restituisce il codice di priorità (CODICE 1–4) in base al colore triage e al tipo dell'ultima visita
- `stampaScheda(coloreAttuale, accessi)` — stampa la scheda completa del paziente con lo storico accessi e le visite

**`Accesso`**
Registra l'ingresso in PS con colore triage e data, collegato al paziente tramite codice fiscale.

**`Clinica`**
Gestisce l'archivio dei pazienti tramite una `HashMap<String, Paziente>` indicizzata per codice fiscale. Espone:
- `registraPaziente(Paziente p)`
- `cercaPaziente(String cf)` — lancia `PazienteNonTrovatoException` se il CF non esiste
- `getPazientiPerColore(String colore)` — filtra i pazienti per codice triage
- `stampaStatistiche()` — mostra la distribuzione per colore e la media visite per paziente
- `ripristinaArchivio(Map<String, Paziente>)` — sostituisce l'archivio corrente

**`GestoreArchivio`**
Gestisce la serializzazione su file di testo con un formato strutturato proprietario basato su marcatori (`### PAZIENTE`, `--- VISITA:Cardiologica`, ecc.). Il parser del metodo `caricaArchivio()` ricostruisce correttamente il grafo di oggetti (paziente → lista visite con polimorfismo) riga per riga.

---

## Funzionalità del menu

| Opzione | Descrizione |
|---|---|
| 1. Registra paziente | Inserisce un nuovo paziente con CF, nome, cognome e primo accesso triage |
| 2. Cerca paziente | Trova un paziente per codice fiscale |
| 3. Aggiungi accesso | Registra un nuovo accesso triage (colore + data) |
| 4. Aggiungi visita | Aggiunge una visita (generica, ortopedica o cardiologica) a un paziente |
| 5. Stampa scheda | Mostra la scheda completa di un paziente con storico accessi e visite |
| 6. Elenca per colore | Filtra e mostra i pazienti per colore triage attuale |
| 7. Statistiche | Distribuzione triage attuale, totale visite e accessi |
| 8. Salva su file | Salva l'archivio su file con nome personalizzato |
| 9. Carica da file | Carica un archivio da file con nome personalizzato |
| 10. Esci | Salva automaticamente su `pazienti.txt` ed esce |

---

## Gestione degli errori

Il sistema gestisce in modo granulare ogni tipo di errore tramite eccezioni, evitando crash del programma:

- **`CodiceFiscaleNonValidoException`** — lanciata se il CF è vuoto o non ha esattamente 16 caratteri
- **`PazienteNonTrovatoException`** — lanciata se il CF cercato non è presente in archivio
- **`IllegalArgumentException`** — per campi obbligatori vuoti o colori triage non validi
- **`DateTimeParseException`** — per date in formato errato (formato atteso: `yyyy-MM-dd`)
- **`NumberFormatException`** — per input numerici non validi (es. frequenza cardiaca)
- **`IOException`** — per errori di lettura/scrittura file

---

## Colori triage e priorità

| Colore | Codice priorità | Condizione |
|---|---|---|
| 🔴 Rosso + visita cardiologica | CODICE 1 — EMERGENZA | Massima urgenza |
| 🔴 Rosso (altri tipi) | CODICE 2 — URGENTE | Alta urgenza |
| 🟡 Giallo | CODICE 3 — PRIORITARIO | Media urgenza |
| 🟢 Verde / ⚪ Bianco | CODICE 4 — STANDARD | Bassa urgenza |
| — | NON VISITATO | Nessuna visita ancora registrata |

---

## Formato del file di archivio

L'archivio viene salvato in un file di testo (default: `pazienti.txt`) con un formato strutturato proprietario:

```
### PAZIENTE
CF:BTTMTT06A01F205Z
NOME:Mattia
COGNOME:Battistella
DATA_REG:2026-03-23
--- ACCESSO
DATA:2026-03-23
COLORE:Rosso
--- FINE_ACCESSO
--- VISITA:Cardiologica
DATA:2026-03-23
MEDICO:Dr. Rossi
DIAGNOSI:Aritmia lieve
FREQUENZA:95
PRESSIONE_SIST:130
PRESSIONE_DIAST:85
--- FINE_VISITA
### FINE_PAZIENTE
```

---

## Come compilare ed eseguire

**Prerequisiti:** JDK 17 o superiore

```bash
# Compilare tutti i file Java
javac *.java

# Avviare il programma
java Main
```

All'avvio il programma cerca automaticamente il file `pazienti.txt` nella stessa cartella. Se non esiste, parte con archivio vuoto.
