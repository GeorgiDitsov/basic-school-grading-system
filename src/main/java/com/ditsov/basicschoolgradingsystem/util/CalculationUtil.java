package com.ditsov.basicschoolgradingsystem.util;

import java.util.List;
import java.util.Optional;

import org.apache.commons.math3.stat.descriptive.moment.Mean;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CalculationUtil {

    	public static Double calculateAverageMark(final List<Double> marks) {
		Mean mean = new Mean();

		mean.incrementAll(marks.stream().mapToDouble(Double::doubleValue).toArray());

		double result = Optional.of(mean.getResult()).filter(value -> !value.isNaN()).orElse(0.0);

		return result;
    	}

}
