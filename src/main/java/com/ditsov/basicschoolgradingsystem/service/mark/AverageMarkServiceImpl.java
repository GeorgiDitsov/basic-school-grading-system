package com.ditsov.basicschoolgradingsystem.service.mark;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import com.ditsov.basicschoolgradingsystem.model.course.Course;
import com.ditsov.basicschoolgradingsystem.model.mark.Mark;
import com.ditsov.basicschoolgradingsystem.model.student.Student;
import com.ditsov.basicschoolgradingsystem.repository.course.CourseRepository;
import com.ditsov.basicschoolgradingsystem.repository.mark.MarkRepository;
import com.ditsov.basicschoolgradingsystem.repository.student.StudentCourseRepository;
import com.ditsov.basicschoolgradingsystem.repository.student.StudentRepository;
import com.ditsov.basicschoolgradingsystem.util.CalculationUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AverageMarkServiceImpl implements AverageMarkService {

    	private final MarkRepository markRepo;
    	private final CourseRepository courseRepo;
	private final StudentRepository studentRepo;
	private final StudentCourseRepository studentCourseRepo;

    	@Override
	public Double getStudentAverageMarkInSingleCourse(final Student student, final Course course) {
    	    	return Precision.round(CalculationUtil
			.calculateAverageMark(markRepo.findAllMarksByStudentAndCourse(student.getId(), course.getId())), 2);
    	}

	@Override
	public Double getStudentAverageMarkInAllCourses(final Student student) {
	    	List<Mark> studentMarks = markRepo.findAllByStudentId(student.getId());
	    	List<Double> averageMarks = new ArrayList<>();

		courseRepo.findAll().forEach(course -> averageMarks.add(CalculationUtil
			.calculateAverageMark(
				studentMarks.stream().filter(mark -> mark.getStudentCourse().getCourse().equals(course))
					.map(Mark::getValue).collect(Collectors.toList()))));


		return Precision.round(CalculationUtil.calculateAverageMark(averageMarks), 2);
	}

	@Override
	public Double getAllStudentsAverageMarkInSingleCourse(final Course course) {
	    	List<Mark> courseMarks = markRepo.findAllByCourseId(course.getId());
	    	List<Double> averageMarks = new ArrayList<>();

		studentRepo.findAll()
			.forEach(student -> averageMarks.add(CalculationUtil.calculateAverageMark(courseMarks.stream()
				.filter(mark -> mark.getStudentCourse().getStudent().equals(student))
				.map(Mark::getValue).collect(Collectors.toList()))));

		return Precision.round(CalculationUtil.calculateAverageMark(averageMarks), 2);
	}

	@Override
	public Double getAllStudentAverageMarkInAllCourses() {
	    	List<Student> students = studentRepo.findAll();
	    	List<Course> courses = courseRepo.findAll();
		List<Mark> marks = markRepo.findAll();
		List<Double> studentAverageMarks = new ArrayList<>();
	    	List<Double> averageMarks = new ArrayList<>();

		students.forEach(
			student -> {
			    studentAverageMarks.clear();

			    courses.forEach(course -> studentAverageMarks.add(CalculationUtil.calculateAverageMark(
				    marks.stream()
					    .filter(mark -> mark.getStudentCourse().getStudent().equals(student)
						    && mark.getStudentCourse().getCourse().equals(course))
					.map(Mark::getValue).collect(Collectors.toList()))));

			    averageMarks.add(CalculationUtil.calculateAverageMark(studentAverageMarks));
			}
		);

	    	return Precision.round(CalculationUtil.calculateAverageMark(averageMarks), 2);
	}

	@Override
	public Double getAllExistingStudentCoursesAverageMark() {
	    	List<Mark> marks = markRepo.findAll();
	    	List<Double> averageMarks = new ArrayList<>();

		studentCourseRepo.findAll()
			.forEach(studentCourse -> averageMarks.add(CalculationUtil.calculateAverageMark(
				marks.stream().filter(mark -> mark.getStudentCourse().equals(studentCourse))
					.map(Mark::getValue).collect(Collectors.toList()))));

		return Precision.round(CalculationUtil.calculateAverageMark(averageMarks), 2);
	}

}
