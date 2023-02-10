package org.sid.courseservice.services;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.sid.courseservice.model.Professor;
//import org.sid.courseservice.security.ClientConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PROFESSORS-SERVICE"/*,configuration = {ClientConfiguration.class}*/)
public interface ProfessorRestClient {

     @GetMapping("/professors/{id}")
    Professor findProfessorById(@PathVariable Long id);
}
