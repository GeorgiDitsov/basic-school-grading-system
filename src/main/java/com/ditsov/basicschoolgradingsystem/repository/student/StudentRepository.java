package com.ditsov.basicschoolgradingsystem.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ditsov.basicschoolgradingsystem.model.student.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
