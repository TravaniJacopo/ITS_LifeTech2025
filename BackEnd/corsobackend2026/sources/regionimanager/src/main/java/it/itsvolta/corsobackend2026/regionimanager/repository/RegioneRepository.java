package it.itsvolta.corsobackend2026.regionimanager.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import it.itsvolta.corsobackend2026.regionimanager.dto.RegioneDto;

@Repository

    
public class RegioneRepository {
     //map con codice regione e contenuto
    private final Map<String, RegioneDto> regioni = new HashMap<>();

    //salva una regione, restituisce false se il codice esiste già
    public boolean  save(RegioneDto regione) {
        
            if (regioni.containsKey(regione.getCodice())) //la regione esiste già
            {
                return false;
            }
            regioni.put(regione.getCodice(), regione);
            return true;
    }

     //restituisce le regione per codice
    public RegioneDto findByCodice(String codice) {
        return regioni.get(codice);
    }

     //restituisce tutte le regioni
   public List<RegioneDto> findAll() 
    {
        return new ArrayList<>(regioni.values());
    }

     //restituisce il numero delle regioni
    public int count() {
        return regioni.size();
    }

    //inizializza le regioni
    public void clear() 
    {
        regioni.clear();
    }
}
