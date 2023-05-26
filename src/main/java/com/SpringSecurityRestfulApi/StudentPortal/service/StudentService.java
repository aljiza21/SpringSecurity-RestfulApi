package com.SpringSecurityRestfulApi.StudentPortal.service;

import com.SpringSecurityRestfulApi.StudentPortal.model.Course;
import com.SpringSecurityRestfulApi.StudentPortal.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student addStudent(Student student);
    List<Student> getAllStudents();
    Optional<Student> getById(Long id);
    void deleteById(Long id);
    Student updateById(Long id, Student updatedStudent);
    Student enroll(Long id, Course course);
    Student drop(Long id, Course course);

}