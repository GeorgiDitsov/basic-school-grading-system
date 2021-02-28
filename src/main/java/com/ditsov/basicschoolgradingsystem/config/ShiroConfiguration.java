package com.ditsov.basicschoolgradingsystem.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.ditsov.basicschoolgradingsystem.config.filter.JWTFilter;
import com.ditsov.basicschoolgradingsystem.config.properties.TokenConfigurationProperties;
import com.ditsov.basicschoolgradingsystem.config.realm.MyRealm;

@Configuration
public class ShiroConfiguration {

    	@Bean
    	public DefaultWebSecurityManager securityManager(MyRealm realm) {
    	    	DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

    	    	securityManager.setRealm(realm);

    	    	DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();

    	    	DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();

    	    	defaultSessionStorageEvaluator.setSessionStorageEnabled(false);

    	    	subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);

    	    	securityManager.setSubjectDAO(subjectDAO);

    	    	return securityManager;
    	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager,
			TokenConfigurationProperties tokenProps) {
	    	ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

	    	Map<String, Filter> filterMap = new HashMap<>();

		filterMap.put("jwt", new JWTFilter(tokenProps));

	    	factoryBean.setFilters(filterMap);
	    	factoryBean.setSecurityManager(securityManager);

	    	Map<String, String> filterRuleMap = new HashMap<>();

		filterRuleMap.put("/rest/api/v1/swagger-ui.html", "anon");
		filterRuleMap.put("/rest/api/v1/login", "anon");
		filterRuleMap.put("/**", "jwt");

	    	factoryBean.setFilterChainDefinitionMap(filterRuleMap);

	    	return factoryBean;
	}


        @Bean
        @DependsOn("lifecycleBeanPostProcessor")
        public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();

    		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);

    		return defaultAdvisorAutoProxyCreator;
        }

        @Bean
        public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    		return new LifecycleBeanPostProcessor();
        }

        @Bean
        public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
    	    		DefaultWebSecurityManager securityManager) {
    		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();

    		advisor.setSecurityManager(securityManager);

    		return advisor;
        }

}
