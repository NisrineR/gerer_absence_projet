package org.sid.professorsservice;

import lombok.Builder;
import org.sid.professorsservice.entities.Professor;
import org.sid.professorsservice.repositories.ProfessorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class ProfessorsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfessorsServiceApplication.class, args);
    }
    @Bean
    public  CommandLineRunner commandLineRunner(ProfessorRepository professorRepository, RepositoryRestConfiguration restConfiguration){
        return args -> {
            restConfiguration.exposeIdsFor(Professor.class);
            professorRepository.saveAll(
                    List.of(
                            Professor.builder().prenom("Mohammed").nom("youssefi").departement("informatique").build(),
                            Professor.builder().prenom("amina").nom("meetok").departement("Math").build(),
                            Professor.builder().prenom("Rachid").nom("hadi").departement("physics").build()
                    )
            );
            professorRepository.findAll().forEach(System.out::println);
        };
    }

}
