package com.douzone.mysite.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.douzone.security.AuthInterceptor;
import com.douzone.security.AuthUserHandlerMethodArgumentResolver;
import com.douzone.security.LoginInterceptor;
import com.douzone.security.LogoutInterceptor;

//@Configuration
public class SecurityConfig extends WebMvcConfigurerAdapter {

	// Argument Resolver
	//	   <!-- validator, conversionService, messageConverter를 자동으로 등록 -->
	//	   <mvc:annotation-driven>
	//	      <mvc:argument-resolvers>
	//	         <bean class="com.douzone.security.AuthUserHandlerMethodArgumentResolver"/>
	//	      </mvc:argument-resolvers>
	//	   </mvc:annotation-driven>
	@Bean
	public HandlerMethodArgumentResolver authUserHandlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(authUserHandlerMethodArgumentResolver());
	}

	// Interceptor	
	//	   <!-- Interceptors -->
	//	   <mvc:interceptors>
	//	      <mvc:interceptor>
	//	         <mvc:mapping path="/user/auth"/>
	//	         <bean class="com.douzone.security.LoginInterceptor" />
	//	      </mvc:interceptor>
	//	      <mvc:interceptor>
	//	         <mvc:mapping path="/user/logout"/>
	//	         <bean class="com.douzone.security.LogoutInterceptor" />
	//	      </mvc:interceptor>      
	//	      <mvc:interceptor>
	//	         <!-- "/**"는 이하 모든 경로에 대해 적용한다는 뜻 -->
	//	         <mvc:mapping path="/**"/>
	//	         <mvc:exclude-mapping path="/user/auth"/>
	//	         <mvc:exclude-mapping path="/user/logout"/>
	//	         <mvc:exclude-mapping path="/assets/**"/>
	//	         <bean class="com.douzone.security.AuthInterceptor" />
	//	      </mvc:interceptor>      
	//	   </mvc:interceptors>
	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
	@Bean
	public HandlerInterceptor logoutInterceptor() {
		return new LogoutInterceptor();
	}
	@Bean
	public HandlerInterceptor authInterceptor() {
		return new AuthInterceptor();
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/user/auth");
		registry.addInterceptor(logoutInterceptor()).addPathPatterns("/user/logout");
		registry.addInterceptor(authInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/auth").excludePathPatterns("/user/logout").excludePathPatterns("/assets/**");
	}


}
