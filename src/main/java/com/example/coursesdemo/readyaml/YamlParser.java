package com.example.coursesdemo.readyaml;

import com.example.coursesdemo.api.Courses;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class YamlParser {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        InputStream is = new YamlParser().getClass().getClassLoader().
                getResourceAsStream("courses.yaml");
        InputStreamReader reader = new InputStreamReader(is);
        //Convert course yaml to Course object using Jackson Object Mapper.
        Courses courses = mapper.readValue(reader, Courses.class);
        courses.getCourses().stream().forEach(
               //Print the name of each course in course object.
                c -> System.out.println(c.getName())
                );
    }
}
