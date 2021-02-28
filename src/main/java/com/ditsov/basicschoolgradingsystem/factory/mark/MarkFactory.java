package com.ditsov.basicschoolgradingsystem.factory.mark;

import com.ditsov.basicschoolgradingsystem.factory.AbstractFactory;
import com.ditsov.basicschoolgradingsystem.model.mark.Mark;
import com.ditsov.basicschoolgradingsystem.model.mark.MarkDTO;
import com.ditsov.basicschoolgradingsystem.model.student.StudentCourse;

public interface MarkFactory extends AbstractFactory<MarkDTO, Mark> {

    	Mark assignValuesToMark(final Mark mark, final Double markValue, final StudentCourse studentCourse);

}
