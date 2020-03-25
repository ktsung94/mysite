package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.douzone.mysite.config.web.FileUploadConfig;
import com.douzone.mysite.config.web.MessageConfig;
import com.douzone.mysite.config.web.MvcConfig;
import com.douzone.mysite.config.web.SecurityConfig;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy	// <aop:aspectj-autoproxy />
// <context:component-scan	base-package="com.douzone.mysite.controller, com.douzone.mysite.exception" />
@ComponentScan({"com.douzone.mysite.controller", "com.douzone.mysite.exception"})
@Import({MvcConfig.class, SecurityConfig.class, MessageConfig.class, FileUploadConfig.class})
public class WebConfig {

}
