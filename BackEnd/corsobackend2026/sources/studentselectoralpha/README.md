# StudentSelectAlpha

Questo progetto parte dalla versione a layer e aggiunge un layer DTO per costruire il JSON secondo le best practice Spring.

La logica resta volutamente semplice:

- lista studenti hardcoded;
- scelta casuale con `Random`;
- endpoint `GET /student/select`.

La differenza importante e che il codice non costruisce piu il JSON con concatenazione di stringhe. Il service crea un oggetto DTO e Spring lo serializza automaticamente in JSON.

## Layer del progetto

### Controller

Package: `it.itsvolta.corsobackend2026.studentselectorbasic.controller`

Il controller gestisce solo la parte HTTP/REST:

- riceve la richiesta web;
- espone l'URL `/student/select`;
- chiama il service;
- restituisce un DTO al client.

Non contiene la logica di selezione dello studente e non costruisce manualmente il JSON. In questo modo la logica puo essere riutilizzata anche da altri punti di ingresso, per esempio una CLI o un altro metodo Java.

### Service

Package: `it.itsvolta.corsobackend2026.studentselectorbasic.service`

Il service contiene la logica applicativa:

- chiede al repository la lista completa degli studenti;
- sceglie un indice casuale;
- recupera lo studente scelto;
- crea il DTO di risposta.

Questo layer rappresenta il "caso d'uso" dell'applicazione. Non deve dipendere dal fatto che sia stato chiamato da un controller REST.

### Repository

Package: `it.itsvolta.corsobackend2026.studentselectorbasic.repository`

Il repository contiene l'accesso ai dati.

In questo esercizio i dati sono ancora hardcoded in una `List`, ma in un progetto reale questo layer sarebbe il punto in cui leggere da database, file, API esterne o altre sorgenti dati.

### DTO

Package: `it.itsvolta.corsobackend2026.studentselectorbasic.dto`

DTO significa Data Transfer Object.

Un DTO e un oggetto semplice usato per trasportare dati tra layer o verso l'esterno dell'applicazione. In questo progetto il DTO `StudentResponse` rappresenta la risposta dell'endpoint:

```json
{
  "student": "Alessandro"
}
```

Il DTO contiene:

- un campo privato `student`;
- un costruttore vuoto;
- un costruttore con `student`;
- getter e setter.

Questa forma e compatibile con le convenzioni JavaBean e permette a Spring/Jackson di leggere il valore tramite `getStudent()` e convertirlo in JSON.

## Perche questi layer sono uno standard

La divisione in `controller`, `service`, `repository` e `dto` e uno schema molto usato nelle applicazioni Spring per separare le responsabilita:

- il controller conosce il web;
- il service conosce la logica dell'applicazione;
- il repository conosce i dati.
- il DTO rappresenta i dati scambiati in input o output.

Questa separazione rende il codice piu facile da leggere, testare e modificare. Per esempio, si potrebbe sostituire la `List` hardcoded con un database modificando il repository, senza cambiare il controller.

## Bean Spring

Un bean Spring e un oggetto creato e gestito dal container Spring.

In questo progetto sono bean Spring:

- `StudentSelectorController`, perche annotato con `@RestController`;
- `StudentSelectorService`, perche annotato con `@Service`;
- `StudentRepository`, perche annotato con `@Repository`.

Spring crea questi oggetti all'avvio dell'applicazione e risolve automaticamente le dipendenze tramite i costruttori. Per esempio, quando crea il controller, gli passa il service; quando crea il service, gli passa il repository.

Di default questi bean sono singleton dentro l'`ApplicationContext`: Spring crea una sola istanza e la riusa.

`StudentResponse`, invece, non e un bean Spring: e un DTO creato dal service quando serve una risposta.

## JSON con best practice

Nel progetto base il JSON veniva costruito manualmente con una stringa, per esempio:

```java
String jsonResponse = "{\"student\":\"" + selectedStudent + "\"}";
```

Questo e un antipattern perche puo creare JSON non valido se i dati contengono caratteri speciali e rende il codice piu fragile.

In `StudentSelectAlpha` il service ritorna:

```java
return new StudentResponse(selectedStudent);
```

Il controller restituisce il DTO:

```java
public StudentResponse selectStudent() {
    return studentSelectorService.selectStudent();
}
```

Spring usa i converter HTTP configurati dallo starter web per serializzare automaticamente l'oggetto in JSON. Il risultato HTTP resta:

```json
{
  "student": "Alessandro"
}
```

ma il codice Java non deve piu concatenare stringhe JSON a mano.
