package com.ditsov.basicschoolgradingsystem.service.course;

import com.ditsov.basicschoolgradingsystem.model.course.Course;

public interface CourseService {

    	Course findCourseById(final Long courseId);

}
