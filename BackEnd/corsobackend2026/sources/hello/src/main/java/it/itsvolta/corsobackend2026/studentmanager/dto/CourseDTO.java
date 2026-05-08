package it.itsvolta.corsobackend2026.studentmanager.dto;

import java.util.List;
public class CourseDTO {
    private String code;
    private String name;
    private String description;
    private String teacherName;
    private int maxStudents;
    private List<String> studentCodes;

    public CourseDTO(){};

    public CourseDTO(String code, String name, String description, String teacherName, int maxStudents, List<String> studentCodes) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.teacherName = teacherName;
        this.maxStudents = maxStudents;
        this.studentCodes = studentCodes;
    }

    public String getCode() {
        return code;
    }

     public String getName() {
        return name;
    }

     public String getDescription() {
        return description;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public List<String> getStudentCodes() {
        return studentCodes;
    }

    public void setCode(String code){
        this.code=code;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public void setTeacherName(String teacherName){
        this.teacherName=teacherName;
    }
    public void setMaxStudents(int maxStudents){
        this.maxStudents=maxStudents;
    }
    public void setStudentCodes(List<String> studentCodes){
        this.studentCodes=studentCodes;
    }
}
