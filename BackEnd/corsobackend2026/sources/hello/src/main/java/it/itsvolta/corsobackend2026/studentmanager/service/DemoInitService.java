package it.itsvolta.corsobackend2026.studentmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.itsvolta.corsobackend2026.studentmanager.dto.CourseDTO;
import it.itsvolta.corsobackend2026.studentmanager.dto.DemoInitDTO;
import it.itsvolta.corsobackend2026.studentmanager.dto.SchoolDTO;
import it.itsvolta.corsobackend2026.studentmanager.dto.StudentDto;

@Service
public class DemoInitService {

    private  final CourseService courseService;
    private  final SchoolService schoolService;
    private  final StudentService studentService;


    public DemoInitService(SchoolService schoolService,CourseService courseService, StudentService studentService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.schoolService = schoolService;
    }

    public DemoInitDTO DemoInitBuilder()
    {
        List<StudentDto> studentCodes = List.of(
            new StudentDto("st1", "Jacopo", "Travani",20),
            new StudentDto("st2", "Marco", "Rossi",22),
            new StudentDto("st3", "Giulia", "Verdi",19),
            new StudentDto("st4", "Luigi", "Bianchi",21),
            new StudentDto("st5", "Anna", "Blu",20),
            new StudentDto("st6", " Stefano", "Neri",23)
        );

        List <CourseDTO> courseCodes = List.of(
            new CourseDTO("co1", "Corso BackEnd", "Corso di programmazione back-end con Java e Spring Boot", "Soranzo",30,List.of("st1","st2","st3")),
            new CourseDTO("co2", "Matematica", "Corso di matematica ", "Zucchini",10,List.of("st4","st5","st6"))
        );

        SchoolDTO school = new SchoolDTO("sc1","ITS LifeTech","via caboto","Trieste","ITSVOLTA",List.of("co1","co2"),List.of("st1","st2","st3","st4","st5","st6"));
    
 
        return new DemoInitDTO(studentCodes, courseCodes, school);
        
    }
    

    public DemoInitDTO getDemo()
    {
        return DemoInitBuilder();
    }


    public void InizializeDemo()
    {
      DemoInitDTO demo = DemoInitBuilder();

      for (StudentDto student : demo.getStudents()) {
        studentService.save(student);
      }
      for (CourseDTO course : demo.getCourses()) {
        courseService.save(course);
      }
      schoolService.save(demo.getSchool());
    }
}
