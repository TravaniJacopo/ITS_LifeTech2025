package it.itsvolta.corsobackend2026.studentmanager.dto;

import java.util.List;

public class SchoolDTO {
    private String code;
    private String name;
    private String address;
    private String city;
    private String principalName;
    private List<String> studentCodes;
    private List<String> courseCodes;


    public SchoolDTO(){};

    public SchoolDTO(String code, String name, String address, String city, String principalName, List<String> studentCodes, List<String> courseCodes) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.city = city;
        this.principalName = principalName;
        this.studentCodes = studentCodes;
        this.courseCodes = courseCodes;
    }

      public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public List<String> getStudentCodes() {
        return studentCodes;
    }

    public void setStudentCodes(List<String> studentCodes) {
        this.studentCodes = studentCodes;
    }

    public List<String> getCourseCodes() {
        return courseCodes;
    }

    public void setCourseCodes(List<String> courseCodes) {
        this.courseCodes = courseCodes;
    }
}


