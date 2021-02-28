package com.ditsov.basicschoolgradingsystem.service.student;

import com.ditsov.basicschoolgradingsystem.model.student.Student;

public interface StudentService {

    	Student findStudentById(final Long studentId);

}