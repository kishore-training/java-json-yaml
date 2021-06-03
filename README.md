# java-json-yaml
Java spring boot application for illustrating JSON and YAML parsing

This is a JAVA application is based on Spring boot. 

The CoursesDemoService.java is the main class which instantiates the spring boot server and it listens on 8098 port by default.This is a maven based application. 

The CourseApiImpl class exposes the REST URL http://localhost:8098/course/JSON-tutorial where JSON-tutorial is the name of the course. You can also notice that we use the jackson library in pom.xml and in CourseApiImpl we parse the student json string to a object using ObjectMapper from jackson library.

The YamlParser
You can build this application by _mvn clean install_ . 
The generated executable jar can be built as java -jar <<jar-name>>
