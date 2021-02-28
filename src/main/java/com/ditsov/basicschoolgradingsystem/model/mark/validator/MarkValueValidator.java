package com.ditsov.basicschoolgradingsystem.model.mark.validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ditsov.basicschoolgradingsystem.model.mark.validator.constraint.MarkValueConstraint;

public class MarkValueValidator implements ConstraintValidator<MarkValueConstraint, Double> {

    	@Override
    	public boolean isValid(Double value, ConstraintValidatorContext context) {
    	    	return Objects.nonNull(value) && !value.isNaN() && value >= 2 && value <= 6;
    	}

}
