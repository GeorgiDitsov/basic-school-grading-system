package com.ditsov.basicschoolgradingsystem.config.filter;

import java.util.Objects;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.BearerToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ditsov.basicschoolgradingsystem.config.properties.TokenConfigurationProperties;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JWTFilter extends BasicHttpAuthenticationFilter {

    	private final TokenConfigurationProperties tokenProps;

    	@Override
    	protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
    	    	HttpServletRequest httpRequest = (HttpServletRequest) request;

    	    	return Objects.nonNull(httpRequest.getHeader(tokenProps.getHeaderName()));
    	}

        @Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response)
			throws AuthenticationException {
    		String authorization = ((HttpServletRequest) request).getHeader(tokenProps.getHeaderName());

		if (Objects.nonNull(authorization)
				&& authorization.startsWith(String.format("%s ", tokenProps.getPrefix()))) {
		    	BearerToken token = new BearerToken(authorization.substring(authorization.indexOf(" ")));
		    	getSubject(request, response).login(token);

			return true;
		}

		return false;
        }

        @Override
        protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if (isLoginAttempt(request, response)) {
		    	try {
		    	    	executeLogin(request, response);
		    	} catch (AuthenticationException e) {
		    	    	((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		    	}
		}

    		return true;
        }

        @Override
        protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
	    	HttpServletRequest httpRequest = (HttpServletRequest) request;
	    	HttpServletResponse httpResponse = (HttpServletResponse) response;

	    	httpResponse.setHeader("Access-control-Allow-Origin", httpRequest.getHeader("Origin"));
	    	httpResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
	    	httpResponse.setHeader("Access-Control-Allow-Headers",
	    		httpRequest.getHeader("Access-Control-Request-Headers"));

	    	if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
	    	    	httpResponse.setStatus(HttpServletResponse.SC_OK);
    	    		return false;
    		}

		return super.preHandle(request, response);
        }

}
