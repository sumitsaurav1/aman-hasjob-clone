package com.aman.majorProject.hasJob.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM users WHERE username=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT username, authority FROM users WHERE username=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/createForm").authenticated()
                .requestMatchers("/viewBookmarks").authenticated()
                .requestMatchers("/application/viewAppliedJobs").authenticated()
                .anyRequest().permitAll()

        ).formLogin(form -> form
                .loginPage("/user/loginForm")
                .loginProcessingUrl("/authenticateTheUser")
                .defaultSuccessUrl("/dashboard")
                .permitAll()

        ).logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/dashboard")
                .permitAll()

        );

        return http.build();
    }
}
