package it.itsvolta.corsobackend2026.studentselectorbasic.service;

import it.itsvolta.corsobackend2026.studentselectorbasic.dto.StudentResponse;
import it.itsvolta.corsobackend2026.studentselectorbasic.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Contains the application logic for selecting a student.
 *
 * This class keeps the simple random selection used in the basic example, but it
 * returns a DTO instead of manually building a JSON string.
 */
@Service
public class StudentSelectorService {

    private final StudentRepository studentRepository;
    private final Random random = new Random();

    public StudentSelectorService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Selects a random student from the repository list and returns a DTO.
     *
     * This method can be called by a REST controller, a CLI, or any other caller
     * that does not need to know anything about HTTP.
     *
     * @return a DTO containing the selected student name
     */
    public StudentResponse selectStudent() {
        List<String> studentNames = studentRepository.findAll();

        int randomIndex = random.nextInt(studentNames.size());
        String selectedStudent = studentNames.get(randomIndex);

        return new StudentResponse(selectedStudent);
    }
}
