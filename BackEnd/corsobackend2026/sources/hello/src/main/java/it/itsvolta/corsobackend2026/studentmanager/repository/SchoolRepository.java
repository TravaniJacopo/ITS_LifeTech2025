package it.itsvolta.corsobackend2026.studentmanager.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import it.itsvolta.corsobackend2026.studentmanager.dto.SchoolDTO;
@Repository
public class SchoolRepository {
        private final Map<String, SchoolDTO> schools = new HashMap<>();


        public void save (SchoolDTO school)
        {
            schools.put(school.getCode(),school);
        }

        public SchoolDTO findByCode(String code) {
            return schools.get(code);
        }

            public List<SchoolDTO> findAll()
        {
        return new ArrayList<>(schools.values());
        }

        public void addStudentToSchool(String schoolCode, String studentCode)
        {
            schools.get(schoolCode).getStudentCodes().add(studentCode);
        }
        public void removeStudentFromSchool(String schoolCode, String studentCode)
        {
            schools.get(schoolCode).getStudentCodes().remove(studentCode);
        }
        public void addCourseToSchool(String schoolCode, String courseCode)
        {
            schools.get(schoolCode).getCourseCodes().add(courseCode);
        }
        public void removeCourseFromSchool(String schoolCode, String courseCode)
        {
            schools.get(schoolCode).getCourseCodes().remove(courseCode);
        }
}
