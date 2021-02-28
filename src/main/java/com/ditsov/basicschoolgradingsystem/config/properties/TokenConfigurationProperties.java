package com.ditsov.basicschoolgradingsystem.config.properties;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "school-grading-system.token")
@Getter
@Setter
public class TokenConfigurationProperties {
    
    	private String headerName;
    	private String prefix;
    	private Duration expiration;
    	private String secretKey;

}
