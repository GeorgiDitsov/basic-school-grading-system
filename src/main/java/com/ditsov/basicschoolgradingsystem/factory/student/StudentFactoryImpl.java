package com.ditsov.basicschoolgradingsystem.factory.student;

import org.springframework.stereotype.Component;

import com.ditsov.basicschoolgradingsystem.model.student.Student;
import com.ditsov.basicschoolgradingsystem.model.student.StudentDTO;

@Component
public class StudentFactoryImpl implements StudentFactory {

        @Override
	public StudentDTO convertToDTO(final Student student) {
	    	StudentDTO dto = new StudentDTO();

	    	dto.setId(student.getId());
	    	dto.setName(student.getName());

	    	return dto;
        }

	@Override
	public Student convertToEntity(StudentDTO t) {
	    	return null;
	}

}
