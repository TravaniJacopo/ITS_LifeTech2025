package it.itsvolta.corsobackend2026.studentselectorbasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.itsvolta.corsobackend2026.studentselectorbasic.dto.StudentResponse;
import it.itsvolta.corsobackend2026.studentselectorbasic.service.StudentSelectorService;

/**
 * StudentSelectorController
 * 
 * This REST controller provides a simple endpoint that returns a randomly selected
 * student name in JSON format.
 * 
 * The controller only knows about HTTP and delegates the application logic to the
 * service layer.
 */
@RestController
@RequestMapping("/student")
public class StudentSelectorController {

    private final StudentSelectorService studentSelectorService;

    public StudentSelectorController(StudentSelectorService studentSelectorService) {
        this.studentSelectorService = studentSelectorService;
    }

    /**
     * Maps HTTP GET requests to the student selection use case.
     * 
     * @return A DTO that Spring serializes as JSON in the format:
     *         {"student":"StudentName"}
     * 
     * GET /student/select
     */
    @GetMapping("/select")
    public StudentResponse selectStudent() {
        return studentSelectorService.selectStudent();
    }
}
