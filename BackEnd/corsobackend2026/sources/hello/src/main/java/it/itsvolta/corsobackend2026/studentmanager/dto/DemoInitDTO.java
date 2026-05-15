package it.itsvolta.corsobackend2026.studentmanager.dto;

import java.util.List;

public class DemoInitDTO {
    private List<StudentDto> students;
    private List<CourseDTO> courses;
    private SchoolDTO school;

    public DemoInitDTO(){};

    public DemoInitDTO(List<StudentDto> students, List<CourseDTO> courses, SchoolDTO school) {
        this.students = students;
        this.courses = courses;
        this.school = school;
    }

    public SchoolDTO getSchool() {
        return school;
    }

    public void setSchool(SchoolDTO school) {
        this.school = school;
    }

    public List<StudentDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDto> students) {
        this.students = students;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }
    
}
