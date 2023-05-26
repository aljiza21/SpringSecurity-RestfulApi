package com.SpringSecurityRestfulApi.StudentPortal.repository;

import com.SpringSecurityRestfulApi.StudentPortal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}