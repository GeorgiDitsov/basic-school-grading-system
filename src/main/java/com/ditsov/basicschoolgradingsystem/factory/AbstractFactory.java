package com.ditsov.basicschoolgradingsystem.factory;

public interface AbstractFactory<T, E> {

    	T convertToDTO(final E e);

	E convertToEntity(final T t);

}
