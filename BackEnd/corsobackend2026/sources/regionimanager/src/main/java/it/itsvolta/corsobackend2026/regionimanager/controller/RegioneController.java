package it.itsvolta.corsobackend2026.regionimanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.itsvolta.corsobackend2026.regionimanager.dto.RegioneDto;
import it.itsvolta.corsobackend2026.regionimanager.dto.RegioniConteggioDto;
import it.itsvolta.corsobackend2026.regionimanager.service.RegioneService;

//espone su path /api/regioni ed espone le varie funzioni
@RestController
@RequestMapping("/api/regioni")
public class RegioneController {

    private final RegioneService regioneService;


        public RegioneController(RegioneService regioneService) 
    {
        this.regioneService = regioneService;
    }

    @PostMapping
    public RegioneDto createProvincia(@RequestBody RegioneDto dto) 
    {
        return regioneService.createRegion(dto);
    }

    @GetMapping
    public List<RegioneDto> findAll() 
    {
        return regioneService.findAll();
    }

    @GetMapping("/{codice}")
    public RegioneDto findRegionByCodice(@PathVariable String codice) 
    {
        return regioneService.findRegionByCodice(codice);
    }

    @GetMapping("/conteggio")
    public RegioniConteggioDto countRegions() {
        return regioneService.countRegions();
    }

    @GetMapping("/random")
    public RegioneDto randomRegion() {
        return regioneService.getRandomRegion();
    }

    @PostMapping("/init")
    public List<RegioneDto> initRegioni() 
    {
        return regioneService.initRegioni();
    }

}