package com.ditsov.basicschoolgradingsystem.factory.mark;

import org.springframework.stereotype.Component;

import com.ditsov.basicschoolgradingsystem.factory.course.CourseFactory;
import com.ditsov.basicschoolgradingsystem.factory.student.StudentFactory;
import com.ditsov.basicschoolgradingsystem.model.mark.Mark;
import com.ditsov.basicschoolgradingsystem.model.mark.MarkDTO;
import com.ditsov.basicschoolgradingsystem.model.student.StudentCourse;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MarkFactoryImpl implements MarkFactory {

    	private final StudentFactory studentFactory;
    	private final CourseFactory courseFactory;

    	@Override
    	public MarkDTO convertToDTO(final Mark mark) {
    	    	MarkDTO dto = new MarkDTO();

    	    	dto.setId(mark.getId());
    	    	dto.setValue(mark.getValue());
    	    	dto.setDate(mark.getDate());
		dto.setStudent(studentFactory.convertToDTO(mark.getStudentCourse().getStudent()));
		dto.setCourse(courseFactory.convertToDTO(mark.getStudentCourse().getCourse()));

		return dto;
    	}

	@Override
	public Mark convertToEntity(final MarkDTO markDto) {
	    	return null;
	}

	@Override
	public Mark assignValuesToMark(final Mark mark, final Double markValue, final StudentCourse studentCourse) {
	    	mark.setValue(markValue);
	    	mark.setStudentCourse(studentCourse);

	    	return mark;
	}

}
