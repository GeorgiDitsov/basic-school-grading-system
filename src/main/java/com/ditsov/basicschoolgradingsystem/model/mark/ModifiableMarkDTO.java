package com.ditsov.basicschoolgradingsystem.model.mark;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.ditsov.basicschoolgradingsystem.model.mark.validator.constraint.MarkValueConstraint;

import lombok.Data;

@Data
public class ModifiableMarkDTO implements Serializable {

    	private static final long serialVersionUID = 7391453219989907747L;

	@MarkValueConstraint
	private Double markValue;

	@NotNull
	private Long studentId;

	@NotNull
	private Long courseId;

}
