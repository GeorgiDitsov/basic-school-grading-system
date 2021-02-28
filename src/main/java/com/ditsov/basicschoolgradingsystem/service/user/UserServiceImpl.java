package com.ditsov.basicschoolgradingsystem.service.user;

import java.util.Objects;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Service;

import com.ditsov.basicschoolgradingsystem.config.properties.TokenConfigurationProperties;
import com.ditsov.basicschoolgradingsystem.model.student.Student;
import com.ditsov.basicschoolgradingsystem.model.user.User;
import com.ditsov.basicschoolgradingsystem.repository.user.UserRepository;
import com.ditsov.basicschoolgradingsystem.util.JsonWebTokenUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    	private final TokenConfigurationProperties tokenProps;

    	private final UserRepository userRepo;

	@Override
	public User findByUsername(final String username) {
	    	return userRepo.findByUsername(username).orElseThrow(
	    		() -> new UnknownAccountException(String.format("User with username %s not found", username)));
	}

    	@Override
        public String attemptLogin(final String username, final String password) {
    	    	User user = findByUsername(username);

		if (user.getPassword().equals(password)) {
		    	return JsonWebTokenUtil.createJWT(user.getId().toString(), user.getUsername(),
		    		tokenProps.getSecretKey(),tokenProps.getExpiration().toMillis());
		}

		throw new IncorrectCredentialsException("Invalid username or password");
        }

	@Override
	public User getCurrentUser() {
	    	String jwt = (String) SecurityUtils.getSubject().getPrincipal();
	    	String username = JsonWebTokenUtil.decodeJWT(jwt, tokenProps.getSecretKey()).getSubject();

	    	return findByUsername(username);
	}

	@Override
	public void assertCurrentUserIsStudent(final Student student) {
	    	User currentUser = getCurrentUser();

		if (!currentUser.getRole().getName().equals("admin")
				&& (Objects.isNull(student.getUser()) || !student.getUser().equals(currentUser))) {
		    	throw new UnauthorizedException("You are not allowed");
	    	}
	}

}
