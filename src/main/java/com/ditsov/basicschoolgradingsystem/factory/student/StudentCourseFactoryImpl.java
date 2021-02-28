package com.ditsov.basicschoolgradingsystem.factory.student;

import org.springframework.stereotype.Component;

import com.ditsov.basicschoolgradingsystem.model.course.Course;
import com.ditsov.basicschoolgradingsystem.model.student.Student;
import com.ditsov.basicschoolgradingsystem.model.student.StudentCourse;

@Component
public class StudentCourseFactoryImpl implements StudentCourseFactory {

    	@Override
	public StudentCourse createStudentCourseFrom(final Student student, final Course course) {
    	    	StudentCourse studentCourse = new StudentCourse();
    	    	
    	    	studentCourse.setStudent(student);
    	    	studentCourse.setCourse(course);
    	    	
    	    	return studentCourse;
    	}

}
