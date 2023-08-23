package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository= studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){
        Optional<Student> studentOptional =   studentRepository.findStudentByEmail(student.getEmail());
//        System.out.println(student);
        if (studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }

        if (student.getDob() == null) {
            student.setDob(LocalDate.of(2000, 1, 1)); // Assign a default date (e.g., January 1, 2000)
        }


        studentRepository.save(student);

    }

    public String deleteStudent(Long studentId){
        boolean exists = studentRepository.existsById(studentId);

        if (!exists){
            throw new IllegalStateException(
                    "student with id" + studentId + " does not exist"
            );
        }
        studentRepository.deleteById(studentId);

        return "Successfully deleted";
    }
}