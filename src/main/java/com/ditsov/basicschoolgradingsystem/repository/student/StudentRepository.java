package com.ditsov.basicschoolgradingsystem.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ditsov.basicschoolgradingsystem.model.student.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
