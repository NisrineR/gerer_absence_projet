package org.sid.courseservice;

import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.sid.courseservice.entities.Course;
import org.sid.courseservice.model.Professor;
import org.sid.courseservice.repositories.CourseRepository;
import org.sid.courseservice.services.ProfessorRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;


import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication

@EnableFeignClients

public class CourseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }
    @Bean
    KeycloakRestTemplate restTemplate(KeycloakClientRequestFactory keycloakClientRequestFactory){
        return new KeycloakRestTemplate(keycloakClientRequestFactory);
    }


    @Bean
    public CommandLineRunner commandLineRunner(CourseRepository courseRepository, ProfessorRestClient professorRestClient, RepositoryRestConfiguration restConfiguration)
    {
        return  args -> {
            restConfiguration.exposeIdsFor(Course.class);
            for (int i=1 ; i< 4 ; i++){
                Long id = (long)i;
                Professor professor=professorRestClient.findProfessorById(id);

                if (professor==null) throw new RuntimeException("professor not found");
                Course course=new Course();
                course.setNom("course"+i);
                course.setProfessorId(id);
                course.setProfessor(professor);
                courseRepository.save(course);
            }
            courseRepository.findAll().forEach(System.out::println);
        };

    }
}
