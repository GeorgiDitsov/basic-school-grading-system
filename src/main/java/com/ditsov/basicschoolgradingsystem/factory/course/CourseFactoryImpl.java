package com.ditsov.basicschoolgradingsystem.factory.course;

import org.springframework.stereotype.Component;

import com.ditsov.basicschoolgradingsystem.model.course.Course;
import com.ditsov.basicschoolgradingsystem.model.course.CourseDTO;

@Component
public class CourseFactoryImpl implements CourseFactory {

    	@Override
    	public CourseDTO convertToDTO(final Course course) {
    	    	CourseDTO dto = new CourseDTO();

    	    	dto.setId(course.getId());
    	    	dto.setName(course.getName());

    	    	return dto;
    	}

	@Override
	public Course convertToEntity(final CourseDTO courseDto) {
	    	return null;
	}

}
