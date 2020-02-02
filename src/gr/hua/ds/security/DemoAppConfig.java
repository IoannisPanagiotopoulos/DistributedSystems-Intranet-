package gr.hua.ds.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class DemoAppConfig implements WebMvcConfigurer {
	private final long MAX_AGE_SECS = 3600;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
				.allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE").maxAge(MAX_AGE_SECS);
		
		registry.addMapping("/student/list").allowedOrigins("*")
			.allowedMethods("GET").maxAge(MAX_AGE_SECS);
		
		registry.addMapping("/student/add").allowedOrigins("*")
			.allowedMethods("POST").maxAge(MAX_AGE_SECS);
		
		registry.addMapping("/student/id/{username}").allowedOrigins("*")
			.allowedMethods("POST").maxAge(MAX_AGE_SECS);
		
		registry.addMapping("/api/**").allowedOrigins("http://localhost:3000")
			.allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE");
		
		registry.addMapping("/student/handle").allowedOrigins("*")
			.allowedMethods("POST").maxAge(MAX_AGE_SECS);
		
		registry.addMapping("/student/addPost").allowedOrigins("*")
			.allowedMethods("POST").maxAge(MAX_AGE_SECS);
		
		
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
