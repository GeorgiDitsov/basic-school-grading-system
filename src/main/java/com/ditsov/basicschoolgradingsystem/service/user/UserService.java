package com.ditsov.basicschoolgradingsystem.service.user;

import com.ditsov.basicschoolgradingsystem.model.student.Student;
import com.ditsov.basicschoolgradingsystem.model.user.User;

public interface UserService {

    	User findByUsername(final String username);

    	String attemptLogin(final String username, final String password);

	User getCurrentUser();

	void assertCurrentUserIsStudent(final Student student);

}
