package it.itsvolta.corsobackend2026.studentmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.itsvolta.corsobackend2026.studentmanager.service.BirreriaService;
import it.itsvolta.corsobackend2026.studentmanager.dto.BirreriaDTO;
import java.util.List;

@RestController
@RequestMapping("/birrerie")
public class BirreriaController {
    private final BirreriaService birreriaService;

    public BirreriaController(BirreriaService birreriaService) {
        this.birreriaService = birreriaService;
    }

    @GetMapping("")
    public List<BirreriaDTO> getBirrerie() {
       try {
            return birreriaService.getBirrerie();
         } catch (Exception e) {
            e.printStackTrace();
            return List.of(); 
         }
    }
}
