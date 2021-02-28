package com.ditsov.basicschoolgradingsystem.service.course;

import org.springframework.stereotype.Service;

import com.ditsov.basicschoolgradingsystem.model.course.Course;
import com.ditsov.basicschoolgradingsystem.repository.course.CourseRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    	private final CourseRepository courseRepo;
    	
    	@Override
	public Course findCourseById(final Long courseId) {
    	    	return courseRepo.findById(courseId).orElseThrow();
    	}

}
