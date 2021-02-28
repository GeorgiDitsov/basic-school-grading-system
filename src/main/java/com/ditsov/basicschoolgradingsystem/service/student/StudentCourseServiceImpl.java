package com.ditsov.basicschoolgradingsystem.service.student;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ditsov.basicschoolgradingsystem.factory.student.StudentCourseFactory;
import com.ditsov.basicschoolgradingsystem.model.course.Course;
import com.ditsov.basicschoolgradingsystem.model.student.Student;
import com.ditsov.basicschoolgradingsystem.model.student.StudentCourse;
import com.ditsov.basicschoolgradingsystem.repository.student.StudentCourseRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentCourseServiceImpl implements StudentCourseService {

    	private final StudentCourseRepository studentCourseRepo;

	private final StudentCourseFactory studentCourseFactory;

        @Override
	@Transactional
        public StudentCourse findStudentCourseByStudentAndCourse(final Student student, final Course course) {
	    	return studentCourseRepo.findDistinctByStudentIdAndCourseId(student.getId(), course.getId())
			.orElseGet(
				() -> saveStudentCourse(studentCourseFactory.createStudentCourseFrom(student, course)));
        }

	@Override
	public StudentCourse saveStudentCourse(final StudentCourse studentCourse) {
	    	return studentCourseRepo.save(studentCourse);
	}

}
