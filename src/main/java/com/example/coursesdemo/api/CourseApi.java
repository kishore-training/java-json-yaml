package com.example.coursesdemo.api;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;

@EnableOpenApi
@EnableSwagger2
@RestController
public abstract class CourseApi {
    public abstract ArrayList<Course> searchByCourseName(String course, SecurityContext securityContext) ;

}
