package org.sid.courseservice.web;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.sid.courseservice.entities.Course;
import org.sid.courseservice.model.Professor;
import org.sid.courseservice.repositories.CourseRepository;
import org.sid.courseservice.services.ProfessorRestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class CourseRestController {
    CourseRepository courseRepository;
    ProfessorRestClient professorRestClient;

    public CourseRestController(CourseRepository courseRepository, ProfessorRestClient professorRestClient) {
        this.courseRepository = courseRepository;
        this.professorRestClient = professorRestClient;
    }


    @GetMapping("/courses/{id}")
    public Course absenceById(@PathVariable Long id, Principal principal){
        /*KeycloakAuthenticationToken authenticationToken=(KeycloakAuthenticationToken) principal;
        AccessToken token= authenticationToken.getAccount().getKeycloakSecurityContext().getToken();
        String username = token.getPreferredUsername();*/
        Course course=courseRepository.findById(id).get();
        course.setProfessor(professorRestClient.findProfessorById(course.getProfessorId()));
        return course;
    }
}
