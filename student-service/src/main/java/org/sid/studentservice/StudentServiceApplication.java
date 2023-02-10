package org.sid.studentservice;

import org.sid.studentservice.entities.Student;
import org.sid.studentservice.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class StudentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentRepository studentRepository, RepositoryRestConfiguration restConfiguration)
    {
        return  args -> {
            restConfiguration.exposeIdsFor(Student.class);
            studentRepository.saveAll(
                    List.of(
                            Student.builder().prenom("nisrine").nom("raihane").code(UUID.randomUUID().toString()).build(),
                            Student.builder().prenom("amine").nom("yousseri").code(UUID.randomUUID().toString()).build(),
                            Student.builder().prenom("hamza").nom("raihane").code(UUID.randomUUID().toString()).build()
                    )
            );
            studentRepository.findAll().forEach(System.out::println);
        };

    }

}
