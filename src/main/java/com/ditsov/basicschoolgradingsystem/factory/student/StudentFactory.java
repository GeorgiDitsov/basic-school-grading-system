package com.ditsov.basicschoolgradingsystem.factory.student;

import com.ditsov.basicschoolgradingsystem.factory.AbstractFactory;
import com.ditsov.basicschoolgradingsystem.model.student.Student;
import com.ditsov.basicschoolgradingsystem.model.student.StudentDTO;

public interface StudentFactory extends AbstractFactory<StudentDTO, Student> {

}
