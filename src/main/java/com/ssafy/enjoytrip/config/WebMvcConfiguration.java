package com.ssafy.enjoytrip.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan(basePackages = { "com.ssafy.enjoytrip.model.dao" })
@EnableAspectJAutoProxy
public class WebMvcConfiguration implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/* '/assets/**'로 호출하는 자원은 '/static/assets/' 폴더 아래에서 찾는다. */
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/").setCachePeriod(60 * 60 * 24 * 365);
	}
}
