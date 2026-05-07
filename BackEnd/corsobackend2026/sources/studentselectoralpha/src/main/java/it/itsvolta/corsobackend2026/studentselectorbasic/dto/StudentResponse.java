package it.itsvolta.corsobackend2026.studentselectorbasic.dto;

/**
 * DTO returned by the student selection use case.
 *
 * Spring reads this object through its getter and serializes it to JSON using the
 * configured HTTP message converters.
 */
public class StudentResponse {

    private String student;

    public StudentResponse() {
    }

    public StudentResponse(String student) {
        this.student = student;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }
}
