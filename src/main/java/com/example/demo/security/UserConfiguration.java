package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class UserConfiguration {

    // @Bean
    // @Autowired
    // public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
    // JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();
    // userDetailsManager.setDataSource(dataSource);
    // return userDetailsManager;
    // }

    @Bean
    @Autowired
    // Config your custom table user
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("SELECT id, pw, active FROM accounts WHERE id = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT id, role FROM roles WHERE id = ?");
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        try {

            http.authorizeHttpRequests(
                    // ** represents any parameter the pass in
                    configure -> configure
                            .anyRequest().permitAll());
            // .requestMatchers(HttpMethod.GET, "students").hasAnyRole("TEACHER", "MANAGER",
            // "ADMIN")
            // .requestMatchers(HttpMethod.GET, "students/**")
            // .hasAnyRole("TEACHER", "MANAGER", "ADMIN")
            // .requestMatchers(HttpMethod.POST, "students").hasAnyRole("MANAGER", "ADMIN")
            // .requestMatchers(HttpMethod.PUT, "students").hasAnyRole("MANAGER", "ADMIN")
            // .requestMatchers(HttpMethod.DELETE, "students/**").hasAnyRole("ADMIN"));

            http.httpBasic(Customizer.withDefaults());

            // Cross site request forgery
            http.csrf(csrf -> csrf.disable());

            return http.build();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

}
