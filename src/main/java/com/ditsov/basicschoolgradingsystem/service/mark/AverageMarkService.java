package com.ditsov.basicschoolgradingsystem.service.mark;

import com.ditsov.basicschoolgradingsystem.model.course.Course;
import com.ditsov.basicschoolgradingsystem.model.student.Student;

public interface AverageMarkService {

    	Double getStudentAverageMarkInSingleCourse(final Student student, final Course course);

	Double getStudentAverageMarkInAllCourses(final Student student);

	Double getAllStudentsAverageMarkInSingleCourse(final Course course);

	Double getAllStudentAverageMarkInAllCourses();

	Double getAllExistingStudentCoursesAverageMark();

}
