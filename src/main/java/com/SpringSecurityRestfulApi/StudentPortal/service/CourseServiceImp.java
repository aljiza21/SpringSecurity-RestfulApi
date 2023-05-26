package com.SpringSecurityRestfulApi.StudentPortal.service;

import com.SpringSecurityRestfulApi.StudentPortal.model.Course;
import com.SpringSecurityRestfulApi.StudentPortal.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImp implements CourseService{
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course addCourse(Course course){
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long id){
        return courseRepository.findById(id);
    }

    @Override
    public void deleteCourse(Long id){
        courseRepository.deleteById(id);
    }

    @Override
    public Course updatedCourse( Long id, Course updatedCourse){
        Course course = courseRepository.findById(id).orElseThrow();
        course.setName(updatedCourse.getName());
        course.setDescription(updatedCourse.getDescription());
        return courseRepository.save(course);
    }
}