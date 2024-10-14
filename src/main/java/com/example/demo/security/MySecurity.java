package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class MySecurity {
        @Bean
        @Autowired
        public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
                JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
                userDetailsManager.setUsersByUsernameQuery("select id, pw, active from accounts where id=?");
                userDetailsManager.setAuthoritiesByUsernameQuery("select id, role from roles where id=?");
                return userDetailsManager;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests(
                                configurer -> configurer
                                                .requestMatchers("/public/**").permitAll()
                                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                                .requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
                                                .requestMatchers("/teacher/**")
                                                .hasAnyRole("ADMIN", "MANAGER", "TEACHER")
                                                .anyRequest().permitAll())
                                .formLogin(
                                                form -> form.loginPage("/showLoginPage")
                                                                .loginProcessingUrl("/authenticateTheUser")
                                                                .permitAll())
                                .logout(
                                                logout -> logout.permitAll())
                                .exceptionHandling(configurer -> configurer.accessDeniedPage("/showPage403"));
                // .exceptionHandling(
                // configurer -> configurer.accessDeniedPage("/showPage403"));

                return http.build();
        }
}
