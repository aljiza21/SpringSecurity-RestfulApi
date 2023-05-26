package com.SpringSecurityRestfulApi.StudentPortal.controller;

import com.SpringSecurityRestfulApi.StudentPortal.model.Course;
import com.SpringSecurityRestfulApi.StudentPortal.model.Student;
import com.SpringSecurityRestfulApi.StudentPortal.repository.StudentRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/studentDetails")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/getById/{id}")
    public Optional<Student> getById(@PathVariable Long id) {
        return studentRepository.findById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id){
        studentRepository.deleteById(id);
    }

    @PutMapping("/updateById/{id}")
    public Student updateById(@PathVariable Long id, @RequestBody Student updateStudent){
        Student student = studentRepository.findById(id).orElseThrow();
        student.setFirstName(updateStudent.getFirstName());
        student.setLastName(updateStudent.getLastName());
        student.setEmail(updateStudent.getEmail());
        student.setCourse(updateStudent.getCourse());
        student.setGpa(updateStudent.getGpa());
        return studentRepository.save(student);
    }
    @PostMapping("/{id}/enroll")
    public Student enroll(@PathVariable Long id, @RequestBody Course course){
        Student student = studentRepository.findById(id).orElseThrow();
        student.setCourse(course);
        return studentRepository.save(student);
    }

    @PostMapping("/{id}/drop")
    public Student drop(@PathVariable Long id, @RequestBody Course course){
        Student student = studentRepository.findById(id).orElseThrow();
        student.setCourse(null);
        return studentRepository.save(student);
    }
}

