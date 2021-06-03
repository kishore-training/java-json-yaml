package com.example.coursesdemo;

import com.example.coursesdemo.api.CourseApiImpl;
import com.example.coursesdemo.api.CourseApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableOpenApi
@EnableSwagger2
@ComponentScan(basePackageClasses = {
		CourseApiImpl.class, CourseApi.class
})
public class CoursesDemoService {

	public static void main(String[] args) {
		SpringApplication.run(CoursesDemoService.class, args);

	}

}
