package com.ditsov.basicschoolgradingsystem.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonWebTokenUtil {

    	public static String createJWT(final String id, final String username, final String secret, long millis) {
		long currentTimeMillis = System.currentTimeMillis();

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
    	    	Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder().setId(id).setSubject(username)
			.setIssuedAt(new Date(currentTimeMillis)).signWith(signatureAlgorithm, signingKey);

		if (millis > 0) {
		    	long expMillis = currentTimeMillis + millis;
			builder.setExpiration(new Date(expMillis));
		}

		return builder.compact();
	}

	public static Claims decodeJWT(final String token, final String secret) {
    	    	Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret))
    	    		.parseClaimsJws(token).getBody();

    	    	return claims;
    	}

}
