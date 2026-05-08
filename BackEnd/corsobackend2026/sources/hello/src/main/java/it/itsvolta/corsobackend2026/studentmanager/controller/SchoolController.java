package it.itsvolta.corsobackend2026.studentmanager.controller;

import it.itsvolta.corsobackend2026.studentmanager.dto.SchoolDTO;
import it.itsvolta.corsobackend2026.studentmanager.service.SchoolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    // 1. Creazione scuola
    @PostMapping
    public void createSchool(@RequestBody SchoolDTO schoolDTO) {
        schoolService.save(schoolDTO);
    }

    // 2. Lettura di una scuola tramite codice
    @GetMapping("/{code}")
    public SchoolDTO getSchoolByCode(@PathVariable String code) {
        return schoolService.findByCode(code);
    }

    // 3. Lettura di tutte le scuole
    @GetMapping
    public List<SchoolDTO> getAllSchools() {
        return schoolService.findAll();
    }

    // 4. Iscrizione studente a una scuola
    @PostMapping("/{schoolCode}/students/{studentCode}")
    public void addStudentToSchool(
            @PathVariable String schoolCode,
            @PathVariable String studentCode
    ) {
        schoolService.addStudentToSchool(schoolCode, studentCode);
    }

    // 5. Rimozione studente da una scuola
    @DeleteMapping("/{schoolCode}/students/{studentCode}")
    public void removeStudentFromSchool(
            @PathVariable String schoolCode,
            @PathVariable String studentCode
    ) {
        schoolService.removeStudentFromSchool(schoolCode, studentCode);
    }

    // 6. Associazione corso a una scuola
    @PostMapping("/{schoolCode}/courses/{courseCode}")
    public void addCourseToSchool(
            @PathVariable String schoolCode,
            @PathVariable String courseCode
    ) {
        schoolService.addCourseToSchool(schoolCode, courseCode);
    }

    // 7. Rimozione corso da una scuola
    @DeleteMapping("/{schoolCode}/courses/{courseCode}")
    public void removeCourseFromSchool(
            @PathVariable String schoolCode,
            @PathVariable String courseCode
    ) {
        schoolService.removeCourseFromSchool(schoolCode, courseCode);
    }
}