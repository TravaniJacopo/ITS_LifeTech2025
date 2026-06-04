package it.itsvolta.corsobackend2026.regionimanager.service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import it.itsvolta.corsobackend2026.regionimanager.dto.RegioneDto;
import it.itsvolta.corsobackend2026.regionimanager.dto.RegioniConteggioDto;

import it.itsvolta.corsobackend2026.regionimanager.repository.RegioneRepository;


@Service
public class RegioneService {
    
    private final RegioneRepository regioneRepository;

	public RegioneService(RegioneRepository regioneRepository)
	{
		this.regioneRepository = regioneRepository;
	}

    //crea la regione
    public RegioneDto createRegion(RegioneDto dto) {
        if (dto.getCodice() == null || dto.getCodice().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Codice mancante");
        }
        if (dto.getNome() == null || dto.getNome().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome mancante");
        }
        //mette il codice senza spazi
        String codice = dto.getCodice().trim();
        dto.setCodice(codice);

        boolean saved = regioneRepository.save(dto);
		if (!saved)
		{
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Regione gia esistente:" );
		}
        return new RegioneDto(codice, dto.getNome());
    }

    //restituisce tutte le regioni
      public List<RegioneDto> findAll() {
        return regioneRepository.findAll();
    }

    //restituisce le regioni per codice
        public RegioneDto findRegionByCodice(String codice) {
        RegioneDto regione = regioneRepository.findByCodice(codice);
        if (codice == null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Codice non inserito" );
        }
        if (regione == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Regione non Trovata" );
        }
        return regione;
    }
    //restituisce un oggetto RegioniConteggioDto con il numero delle regioni
    public RegioniConteggioDto countRegions() {
        return new RegioniConteggioDto(regioneRepository.count());
    }

    //restituisce una regione casuale
    public RegioneDto getRandomRegion() {
        List<RegioneDto> regions = findAll();
        if (regions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nessuna regione trovata" );
        }
        Random random = new Random();
        int index = random.nextInt(regions.size());//crea un numero random grande al massimo come il numero delle regioni
        return regions.get(index);
    }

    //inizzializza delle regioni
    public List<RegioneDto> initRegioni()
     {  
            regioneRepository.clear();
        	List<RegioneDto> seed = Arrays.asList(
			new RegioneDto("lombardia","Lombardia"),
			new RegioneDto("piemonte", "Piemonte"),
			new RegioneDto("sardegna", "Sardegna"),
			new RegioneDto("emiliaRomagna","Emilia Romagna"),
            new RegioneDto("liguria","Liguria"),
            new RegioneDto("friuliVeneziaGiulia","Friuli Venezia Giulia")
		);
        for (RegioneDto dto : seed)
        {
            regioneRepository.save(dto);
        }
        return seed;
     }

}
