package com.ditsov.basicschoolgradingsystem.model.mark;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ditsov.basicschoolgradingsystem.model.course.CourseDTO;
import com.ditsov.basicschoolgradingsystem.model.student.StudentDTO;

import lombok.Data;

@Data
public class MarkDTO implements Serializable {

    	private static final long serialVersionUID = -4786489437728561429L;

    	private Long id;
    	private Double value;
    	private LocalDateTime date;
	private StudentDTO student;
	private CourseDTO course;

}
