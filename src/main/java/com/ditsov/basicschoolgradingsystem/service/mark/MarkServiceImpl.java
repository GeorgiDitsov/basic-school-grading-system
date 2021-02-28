package com.ditsov.basicschoolgradingsystem.service.mark;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ditsov.basicschoolgradingsystem.factory.mark.MarkFactory;
import com.ditsov.basicschoolgradingsystem.model.course.Course;
import com.ditsov.basicschoolgradingsystem.model.mark.Mark;
import com.ditsov.basicschoolgradingsystem.model.mark.MarkDTO;
import com.ditsov.basicschoolgradingsystem.model.student.Student;
import com.ditsov.basicschoolgradingsystem.model.student.StudentCourse;
import com.ditsov.basicschoolgradingsystem.repository.mark.MarkRepository;
import com.ditsov.basicschoolgradingsystem.repository.student.StudentCourseRepository;
import com.ditsov.basicschoolgradingsystem.util.CalculationUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MarkServiceImpl implements MarkService {

    	private final MarkRepository markRepo;
	private final StudentCourseRepository studentCourseRepo;

    	private final MarkFactory markFactory;


    	@Override
    	public List<MarkDTO> findAllMarks() {
    	    	return markRepo.findAll().stream().map(markFactory::convertToDTO).collect(Collectors.toList());
    	}

	@Override
	public MarkDTO findMark(final Long markId) {
	    	return markRepo.findById(markId).map(markFactory::convertToDTO).orElseThrow();
	}

	@Override
	@Transactional
	public MarkDTO saveMark(final Optional<Long> markId, final Double markValue, final StudentCourse studentCourse) {
	    	Mark mark = markId.map(id -> markRepo.findById(id).orElseThrow()).orElseGet(Mark::new);

		return markFactory
			.convertToDTO(markRepo.save(markFactory.assignValuesToMark(mark, markValue, studentCourse)));
	}

	@Async
	@Override
	@Transactional
	public void deleteMark(final Long markId) {
	    	markRepo.findById(markId).ifPresent(mark -> {
	    	    	Long studentCourseId = mark.getStudentCourse().getId();

	    	    	markRepo.delete(mark);

	    	    	if (!markRepo.existsByStudentCourseId(studentCourseId)) {
	    	    	    	studentCourseRepo.deleteById(studentCourseId);

	    	    	    	log.info("Student-Course relationship with id {} deleted successfully.", studentCourseId);
	    	    	}
		});

		log.info("Mark with id {} deleted successfully.", markId);
	}

	@Override
	public Double getAverageMarkOfCourse(final Course course) {
	    	List<Mark> courseMarks = markRepo.findAllByCourseId(course.getId());
	    	List<Double> averageMarks = new ArrayList<>();

		courseMarks.forEach(mark -> {

		});

		for (Student student : courseMarks.stream().map(mark -> mark.getStudentCourse().getStudent())
				.collect(Collectors.toList())) {
	    	    	List<Double> marks = courseMarks.stream()
	    	    		.filter(mark -> mark.getStudentCourse().getStudent().equals(student)).map(Mark::getValue)
	    	    		.collect(Collectors.toList());

			averageMarks.add(CalculationUtil.calculateAverageMark(marks));
	    	}

		    return CalculationUtil.calculateAverageMark(averageMarks);
	}

}
