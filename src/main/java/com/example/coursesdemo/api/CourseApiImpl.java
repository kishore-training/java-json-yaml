package com.example.coursesdemo.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletConfig;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Path("/course")
@RequestMapping("/course")
@EnableOpenApi
@EnableSwagger2
@RestController
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2021-04-04T07:48:58.250Z[GMT]")
public class CourseApiImpl {

    public CourseApiImpl(@Context ServletConfig servletContext) {

    }

    private static final String STUDENT_URL = "http://localhost:3000/student/";

    @GetMapping("/{course}")

    @Produces({"application/json"})

    /** The public facing Rest controller to retrieve courses along with student details for the course
     * for a specified course name
     *
     */
    public Course searchByCourseName(@PathVariable("course") String course)
            throws NotFoundException {
        Course courseObj = new Course();

        try {
            //Get the list of Students for the course from nodejs service
            String studentsJson = getStudentsFromStudentService(course);
            System.out.println("Student JSON string"+ studentsJson);
            //convert student json into Student object by using Jackson Parser
            ObjectMapper mapper = new ObjectMapper();
            Student[] students = mapper.readValue(studentsJson, Student[].class);

            //Get the Course object from local database
            courseObj = getCourseObjectFromLocalDatabase(course);

            //set the list of students obtained from nodeJS service into course object.
            courseObj.setStudents(Arrays.asList(students));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseObj;
    }

    private String getStudentsFromStudentService(String courseName) {
        StringBuilder builder = new StringBuilder();
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(STUDENT_URL + courseName);
            HttpResponse response = client.execute(request);

// Get the response
            BufferedReader rd = new BufferedReader
                    (new InputStreamReader(
                            response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
                builder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private Course getCourseObjectFromLocalDatabase(String courseName) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("database/courses.json");
        InputStreamReader reader = new InputStreamReader(is);
        Courses courses = mapper.readValue(reader, Courses.class);
        List<Course> coursesList = courses.getCourses().stream().filter(n -> n.getName().equals(courseName)).collect(Collectors.toList());
        Course courseObj = coursesList.get(0);
        return courseObj;
    }


}
