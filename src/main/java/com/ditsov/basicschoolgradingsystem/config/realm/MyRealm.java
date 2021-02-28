package com.ditsov.basicschoolgradingsystem.config.realm;

import java.util.Objects;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.BearerToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.ditsov.basicschoolgradingsystem.config.properties.TokenConfigurationProperties;
import com.ditsov.basicschoolgradingsystem.model.user.User;
import com.ditsov.basicschoolgradingsystem.service.user.UserService;
import com.ditsov.basicschoolgradingsystem.util.JsonWebTokenUtil;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MyRealm extends AuthorizingRealm {

    	private final TokenConfigurationProperties tokenProps;

    	private final UserService userService;

    	@Override
    	public boolean supports(AuthenticationToken token) {
    	    	return token instanceof BearerToken;
    	}

        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
            	if (Objects.isNull(principals)) {
            	    	throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
            	}

		String token = principals.toString();
		String username = JsonWebTokenUtil.decodeJWT(token, tokenProps.getSecretKey()).getSubject();
		User user = userService.findByUsername(username);

		return new SimpleAuthorizationInfo(Set.of(user.getRole().getName()));
        }

        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String jwt = (String) token.getCredentials();

		if (Objects.isNull(jwt)) {
		    	throw new AuthenticationException("Null tokens are not allowed by this realm.");
		}

		String username = JsonWebTokenUtil.decodeJWT(jwt, tokenProps.getSecretKey()).getSubject();
		userService.findByUsername(username);

		return new SimpleAuthenticationInfo(jwt, jwt, getName());
        }

}
