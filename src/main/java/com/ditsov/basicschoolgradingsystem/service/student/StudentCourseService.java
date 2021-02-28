package com.ditsov.basicschoolgradingsystem.service.student;

import com.ditsov.basicschoolgradingsystem.model.course.Course;
import com.ditsov.basicschoolgradingsystem.model.student.Student;
import com.ditsov.basicschoolgradingsystem.model.student.StudentCourse;

public interface StudentCourseService {

    	StudentCourse findStudentCourseByStudentAndCourse(final Student student, final Course course);

	StudentCourse saveStudentCourse(final StudentCourse studentCourse);

}
