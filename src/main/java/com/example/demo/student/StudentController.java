package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    String StudentID = "studentId";


    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService =  studentService;
    }
    @GetMapping("/get-details")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping(("/add-details"))
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping("/delete-by-id/{studentId}")
    public String deleteStudent(
            @PathVariable("studentId") Long studentId) {
        return studentService.deleteStudent(studentId);
    }

}
