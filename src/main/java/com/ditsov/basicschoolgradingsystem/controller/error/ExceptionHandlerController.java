package com.ditsov.basicschoolgradingsystem.controller.error;

import java.util.NoSuchElementException;

import javax.validation.ValidationException;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    	@ExceptionHandler(value = { DataIntegrityViolationException.class, ValidationException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleBadRequest(final Exception exception) {
    	    	return exception.getMessage();
    	}

    	@ExceptionHandler(value = { NoSuchElementException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
    	public String handleNotFound(final Exception exception) {
    	    	return "Resource not found";
    	}

	@ExceptionHandler(value = { ShiroException.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String handleUnauthorized(final Exception exception) {

	    	if (exception instanceof IncorrectCredentialsException
	    		|| exception instanceof UnknownAccountException
	    			|| exception instanceof UnauthorizedException) {
	    	    	return exception.getMessage();
	    	}

	    	return "Unauthorized request";
    	}

}
