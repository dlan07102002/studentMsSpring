package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class UserConfiguration {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user1 = User.withUsername("ducklan").password("{noop}123456").roles("teacher").build();
        UserDetails user2 = User.withUsername("ngnhi").password("{noop}123456").roles("manager").build();
        UserDetails user3 = User.withUsername("thdat").password("{noop}123456").roles("admin").build();
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        try {
            http.authorizeHttpRequests(
                    // ** represents any parameter the pass in
                    configure -> configure
                            .requestMatchers(HttpMethod.GET, "students").hasAnyRole("teacher", "manager", "admin")
                            .requestMatchers(HttpMethod.GET, "students/**")
                            .hasAnyRole("teacher", "manager", "admin")
                            .requestMatchers(HttpMethod.POST, "students").hasAnyRole("manager", "admin")
                            .requestMatchers(HttpMethod.PUT, "students").hasAnyRole("manager", "admin")
                            .requestMatchers(HttpMethod.DELETE, "students/**").hasAnyRole("admin"));
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
