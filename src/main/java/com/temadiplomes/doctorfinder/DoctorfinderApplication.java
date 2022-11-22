package com.temadiplomes.doctorfinder;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DoctorfinderApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DoctorfinderApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DoctorfinderApplication.class);
	}
}
