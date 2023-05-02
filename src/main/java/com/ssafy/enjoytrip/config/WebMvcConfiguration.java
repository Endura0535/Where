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
		/* '/js/**'로 호출하는 자원은 '/static/js/' 폴더 아래에서 찾는다. */
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/assets/js/").setCachePeriod(60 * 60 * 24 * 365);
		/* '/css/**'로 호출하는 자원은 '/static/css/' 폴더 아래에서 찾는다. */
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/assets/css/").setCachePeriod(60 * 60 * 24 * 365);
		/* '/img/**'로 호출하는 자원은 '/static/img/' 폴더 아래에서 찾는다. */
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/assets/img/").setCachePeriod(60 * 60 * 24 * 365);
		/* '/font/**'로 호출하는 자원은 '/static/font/' 폴더 아래에서 찾는다. */
		registry.addResourceHandler("/font/**").addResourceLocations("classpath:/static/assets/font/").setCachePeriod(60 * 60 * 24 * 365);
	}
}
