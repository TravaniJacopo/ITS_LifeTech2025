package it.itsvolta.corsobackend2026.studentmanager.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

import it.itsvolta.corsobackend2026.studentmanager.dto.CourseDTO;

@Repository
public class CourseRepository {
    private final Map<String, CourseDTO> courses = new HashMap<>();

    public void save (CourseDTO course)
    {
        courses.put(course.getCode(),course);
    }

    public CourseDTO findByCode(String code)
    {
        return courses.get(code);
    }

    public List<CourseDTO> findAll()
    {

        return new ArrayList<>(courses.values());
    }

    public void addStudentToCourse(String courseCode,String studentCode)
    {
    courses.get(courseCode).getStudentCodes().add(studentCode);
    }

    public void removeStudentFromCourse(String courseCode, String studentCode)
    {
        courses.get(courseCode).getStudentCodes().remove(studentCode);
    }


}
