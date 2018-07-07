package com.lukamaletin.cdss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.lukamaletin.cdss.util.Constants.*;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable();
        http.httpBasic().disable();
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(API_USERS).access("hasAuthority('ADMIN')")
                .antMatchers(API_MEDICS).access("hasAuthority('ADMIN')")
                .antMatchers(API_PATIENTS).access("hasAuthority('MEDIC')")
                .antMatchers(API_THERAPIES).access("hasAuthority('MEDIC')")
                .antMatchers(HttpMethod.GET,
                        API_DISEASES, API_INGREDIENTS, API_MEDICATIONS, API_SYMPTOMS).access("isAuthenticated()")
                .antMatchers(HttpMethod.POST,
                        API_DISEASES, API_INGREDIENTS, API_MEDICATIONS, API_SYMPTOMS).access("hasAuthority('ADMIN')")
                .antMatchers(HttpMethod.PUT,
                        API_DISEASES, API_INGREDIENTS, API_MEDICATIONS, API_SYMPTOMS).access("hasAuthority('ADMIN')")
                .antMatchers(HttpMethod.DELETE,
                        API_DISEASES, API_INGREDIENTS, API_MEDICATIONS, API_SYMPTOMS).access("hasAuthority('ADMIN')");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
