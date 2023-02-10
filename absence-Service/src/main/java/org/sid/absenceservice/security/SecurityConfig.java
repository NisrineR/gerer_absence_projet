package org.sid.absenceservice.security;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
@KeycloakConfiguration
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.authenticationProvider(keycloakAuthenticationProvider());
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
            super.configure(http);

            http.csrf().disable();
            http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
            http.authorizeRequests().antMatchers("/absences/*").permitAll();
            http.authorizeRequests().antMatchers("/absences*").permitAll();
            http.headers().frameOptions().disable();
            http.authorizeRequests().anyRequest().authenticated();

//                 http.authorizeHttpRequests((requests) -> requests.requestMatchers("/h2-console/**").permitAll().anyRequest().authenticated());

    }

}
