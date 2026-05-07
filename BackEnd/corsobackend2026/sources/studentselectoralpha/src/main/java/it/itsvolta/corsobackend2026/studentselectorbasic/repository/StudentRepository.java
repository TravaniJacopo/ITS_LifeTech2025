package it.itsvolta.corsobackend2026.studentselectorbasic.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides access to student data.
 *
 * In this exercise the data source is still a hardcoded List, exactly as in the
 * basic project. The repository layer is introduced only to show where data access
 * code normally lives.
 */
@Repository
public class StudentRepository {

    /**
     * Returns the complete hardcoded list of students.
     *
     * @return all available student names
     */
    public List<String> findAll() {
        List<String> studentNames = new ArrayList<>();
        studentNames.add("Alessandro");
        studentNames.add("Andrea");
        studentNames.add("Angela");

        return studentNames;
    }
}
