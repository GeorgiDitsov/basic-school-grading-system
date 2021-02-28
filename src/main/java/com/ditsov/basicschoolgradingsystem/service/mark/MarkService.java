package com.ditsov.basicschoolgradingsystem.service.mark;

import java.util.List;
import java.util.Optional;

import com.ditsov.basicschoolgradingsystem.model.course.Course;
import com.ditsov.basicschoolgradingsystem.model.mark.MarkDTO;
import com.ditsov.basicschoolgradingsystem.model.student.StudentCourse;

public interface MarkService {

    	List<MarkDTO> findAllMarks();

	MarkDTO findMark(final Long markId);

	MarkDTO saveMark(final Optional<Long> markId, final Double markValue, final StudentCourse studentCourse);

	void deleteMark(final Long markId);

	Double getAverageMarkOfCourse(final Course course);

}
