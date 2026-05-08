package it.itsvolta.corsobackend2026.studentmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.itsvolta.corsobackend2026.studentmanager.dto.SchoolDTO;
import it.itsvolta.corsobackend2026.studentmanager.repository.CourseRepository;
import it.itsvolta.corsobackend2026.studentmanager.repository.StudentRepository;
import it.itsvolta.corsobackend2026.studentmanager.repository.SchoolRepository;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public SchoolService(SchoolRepository schoolRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.schoolRepository = schoolRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

        public SchoolDTO findByCode(String code) {
        return schoolRepository.findByCode(code);
    }

       public void save (SchoolDTO school) 
    {
        if (schoolRepository.findByCode(school.getCode()) != null )
        {
            throw new IllegalArgumentException("School with code " + school.getCode() + " already exists.");
        }
        schoolRepository.save(school);
    }

        public List<SchoolDTO> findAll() {
        return schoolRepository.findAll();
    }

    public void addStudentToSchool(String schoolCode, String studentCode) {
        if (schoolRepository.findByCode(schoolCode) == null) {
            throw new IllegalArgumentException("School with code " + schoolCode + " does not exist.");
        }
        if (studentRepository.findByCode(studentCode) == null) {
            throw new IllegalArgumentException("Student with code " + studentCode + " does not exist.");
        }
        schoolRepository.addStudentToSchool(schoolCode, studentCode);
    }

    public void removeStudentFromSchool(String schoolCode, String studentCode) {
        if (schoolRepository.findByCode(schoolCode) == null) {
            throw new IllegalArgumentException("School with code " + schoolCode + " does not exist.");
        }
        if (studentRepository.findByCode(studentCode) == null) {
            throw new IllegalArgumentException("Student with code " + studentCode + " does not exist.");
        }
        schoolRepository.removeStudentFromSchool(schoolCode, studentCode);
    }

    public void addCourseToSchool(String schoolCode, String courseCode) {
        if (schoolRepository.findByCode(schoolCode) == null) {
            throw new IllegalArgumentException("School with code " + schoolCode + " does not exist.");
        }
        if (courseRepository.findByCode(courseCode) == null) {
            throw new IllegalArgumentException("Course with code " + courseCode + " does not exist.");
        }
        schoolRepository.addCourseToSchool(schoolCode, courseCode);
    }

    public void removeCourseFromSchool(String schoolCode, String courseCode) {
        if (schoolRepository.findByCode(schoolCode) == null) {
            throw new IllegalArgumentException("School with code " + schoolCode + " does not exist.");
        }
        if (courseRepository.findByCode(courseCode) == null) {
            throw new IllegalArgumentException("Course with code " + courseCode + " does not exist.");
        }
        schoolRepository.removeCourseFromSchool(schoolCode, courseCode);
    }

}