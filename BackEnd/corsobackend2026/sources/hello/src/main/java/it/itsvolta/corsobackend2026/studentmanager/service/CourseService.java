package it.itsvolta.corsobackend2026.studentmanager.service;

import org.springframework.stereotype.Service;
import java.util.List;

import it.itsvolta.corsobackend2026.studentmanager.dto.CourseDTO;

import it.itsvolta.corsobackend2026.studentmanager.repository.CourseRepository;
import it.itsvolta.corsobackend2026.studentmanager.repository.StudentRepository;
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public CourseDTO findByCode(String code) {
        return courseRepository.findByCode(code);
    }

    public void save (CourseDTO course) 
    {
        if (courseRepository.findByCode(course.getCode()) != null )
        {
            throw new IllegalArgumentException("Course with code " + course.getCode() + " already exists.");
        }
        courseRepository.save(course);
    }

    public void addStudentToCourse(String courseCode, String studentCode) {
        if (courseRepository.findByCode(courseCode) == null) {
            throw new IllegalArgumentException("Course with code " + courseCode + " does not exist.");
        }
        if (studentRepository.findByCode(studentCode) == null) {
            throw new IllegalArgumentException("Student with code " + studentCode + " does not exist.");
        }

        if (courseRepository.findByCode(courseCode).getStudentCodes().size() >= courseRepository.findByCode(courseCode).getMaxStudents()) {
            throw new IllegalArgumentException("Course with code " + courseCode + " is already full.");
        }
        for (CourseDTO course : courseRepository.findAll()) {
            if (course.getStudentCodes().contains(studentCode)) {
                throw new IllegalArgumentException("Student with code " + studentCode + " is already enrolled in course " + courseCode + ".");
            }
        }
        courseRepository.addStudentToCourse(courseCode, studentCode);

    }

    public void removeStudentFromCourse(String courseCode, String studentCode) {
        if (courseRepository.findByCode(courseCode) == null) {
            throw new IllegalArgumentException("Course with code " + courseCode + " does not exist.");
        }
        if (studentRepository.findByCode(studentCode) == null) {
            throw new IllegalArgumentException("Student with code " + studentCode + " does not exist.");
        }
        courseRepository.removeStudentFromCourse(courseCode, studentCode);
    }

    public List<CourseDTO> findAll() {
        return courseRepository.findAll();
    }
    
}
