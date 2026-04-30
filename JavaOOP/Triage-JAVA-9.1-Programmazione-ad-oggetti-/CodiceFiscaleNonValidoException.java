//esercitazione: Sistema Triage Pronto Soccorso -- UF 9.1 Programmazione ad oggetti in Java
//Battistella - Travani - Persico

//errore quando il codice fiscale non è valido
public class CodiceFiscaleNonValidoException extends Exception {
    
    public CodiceFiscaleNonValidoException(String message) //costruttore con messaggio di errore
    {
        super(message);
    }
}