//esercitazione: Sistema Triage Pronto Soccorso -- UF 9.1 Programmazione ad oggetti in Java
//Battistella - Travani - Persico

//errore quando il paziente non esiste in archivio
public class PazienteNonTrovatoException extends Exception {
    
    public PazienteNonTrovatoException(String message) //costruttore con messaggio di errore
    {
        super(message);
    }
}