package com.ditsov.basicschoolgradingsystem.factory.student;

import com.ditsov.basicschoolgradingsystem.model.course.Course;
import com.ditsov.basicschoolgradingsystem.model.student.Student;
import com.ditsov.basicschoolgradingsystem.model.student.StudentCourse;

public interface StudentCourseFactory {
    
    	StudentCourse createStudentCourseFrom(final Student student, final Course course);

}
