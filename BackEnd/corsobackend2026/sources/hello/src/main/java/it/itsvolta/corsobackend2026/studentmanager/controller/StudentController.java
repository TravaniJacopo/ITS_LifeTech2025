package it.itsvolta.corsobackend2026.studentmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.itsvolta.corsobackend2026.studentmanager.dto.StudentDto;
import it.itsvolta.corsobackend2026.studentmanager.service.StudentService;


@RestController
public class StudentController {
    private final StudentService studentService;
    
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/{code}")
    public StudentDto getStudentByCode(@PathVariable String code) {
        return studentService.findByCode(code);
    }

    @GetMapping("/students")
    public List<StudentDto> getAllStudents() {
        return studentService.findAll();
    }


    @PostMapping("/students")
    public void createStudent(@RequestBody StudentDto student) {
        studentService.save(student);
    }   
}
