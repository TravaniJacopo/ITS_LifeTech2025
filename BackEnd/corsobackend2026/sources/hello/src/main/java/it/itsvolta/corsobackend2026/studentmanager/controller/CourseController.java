package it.itsvolta.corsobackend2026.studentmanager.controller;

import org.springframework.web.bind.annotation.RestController;
import it.itsvolta.corsobackend2026.studentmanager.service.CourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import it.itsvolta.corsobackend2026.studentmanager.dto.CourseDTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
@RestController("")
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @PostMapping("/courses")
    public void createCourse(@RequestBody CourseDTO course) {
        courseService.save(course);
    }

    @GetMapping("/courses/{courseCode}")
    public CourseDTO getCourseByCode(@PathVariable String courseCode) {
        return courseService.findByCode(courseCode);

    }

    @GetMapping("/courses")
    public List<CourseDTO> getAllCourses() {
        return courseService.findAll();
     }


     @PostMapping("/courses/{courseCode}/students/{studentCode}")
     public void addStudentToCourse(@PathVariable String courseCode, @PathVariable String studentCode) {
       courseService.addStudentToCourse(courseCode, studentCode);
     }
   
 
     @DeleteMapping("/courses/{courseCode}/students/{studentCode}")
     public void removeStudentFromCourse(@PathVariable String courseCode, @PathVariable String studentCode) {
        courseService.removeStudentFromCourse(courseCode, studentCode);
     }

}
