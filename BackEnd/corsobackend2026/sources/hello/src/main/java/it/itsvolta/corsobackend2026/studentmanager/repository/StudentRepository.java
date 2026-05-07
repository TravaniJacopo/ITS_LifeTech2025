package it.itsvolta.corsobackend2026.studentmanager.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import it.itsvolta.corsobackend2026.studentmanager.dto.StudentDto;
@Repository
public class StudentRepository {

    private final Map<String, StudentDto> students = new HashMap<>();


    public void save (StudentDto student) {
        students.put(student.getCode(), student);
    }
    
    public StudentDto findByCode (String code) {
        return students.get(code);
    }

    public List<StudentDto> findAll() {
        return new ArrayList<>(students.values());
    }
}
