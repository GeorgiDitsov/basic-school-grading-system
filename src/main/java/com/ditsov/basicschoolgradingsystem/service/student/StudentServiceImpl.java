package com.ditsov.basicschoolgradingsystem.service.student;

import org.springframework.stereotype.Service;

import com.ditsov.basicschoolgradingsystem.model.student.Student;
import com.ditsov.basicschoolgradingsystem.repository.student.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    	private final StudentRepository studentRepo;

        @Override
	public Student findStudentById(final Long studentId) {
    		return studentRepo.findById(studentId).orElseThrow();
        }

}
