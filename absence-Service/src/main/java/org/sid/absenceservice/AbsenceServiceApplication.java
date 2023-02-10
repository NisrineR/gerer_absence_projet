package org.sid.absenceservice;

import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.sid.absenceservice.entities.Absence;
import org.sid.absenceservice.model.Course;
import org.sid.absenceservice.model.Student;
import org.sid.absenceservice.repositories.AbsenceRepository;
import org.sid.absenceservice.services.CourseRestClient;
import org.sid.absenceservice.services.StudentRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Collection;
import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class AbsenceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AbsenceServiceApplication.class, args);
    }
    @Bean
    KeycloakRestTemplate restTemplate(KeycloakClientRequestFactory keycloakClientRequestFactory){
        return new KeycloakRestTemplate(keycloakClientRequestFactory);
    }
    @Bean
    CommandLineRunner start(AbsenceRepository absenceRepository,
                            CourseRestClient courseRestClient,
                            StudentRestClient studentRestClient,
                            RepositoryRestConfiguration restConfiguration
    ){
        return args -> {
            restConfiguration.exposeIdsFor(Absence.class);
            for (int i=1 ; i<4 ; i++){
                Long id = (long)i;
                Student student=studentRestClient.findStudentById(id);
                for (int j = 1; j < 4; j++) {
                    Long id1 = (long)j;
                    Course course=courseRestClient.findCourseByID(id1);
                    if (student==null) throw new RuntimeException("student not found");
                    if (course==null) throw new RuntimeException("course not found");
                    Absence absence=new Absence();

                    absence.setDate(new Date());
                    absence.setCourseId(id1);
                    absence.setStudentId(id);
                    absence.setCourse(course);
                    absence.setStudent(student);
                    absenceRepository.save(absence);}


                absenceRepository.findAll().forEach(System.out::println);
            }
        };
    }
}
