package it.itsvolta.corsobackend2026.studentmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.itsvolta.corsobackend2026.studentmanager.dto.StudentDto;
import it.itsvolta.corsobackend2026.studentmanager.repository.StudentRepository;
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void save (StudentDto student) {
        studentRepository.save(student);
    }
        public StudentDto findByCode (String code) {
            return studentRepository.findByCode(code);
        }
        public List<StudentDto> findAll() {
            return studentRepository.findAll();
        }
    
}
