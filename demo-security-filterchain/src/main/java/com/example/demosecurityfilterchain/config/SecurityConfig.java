package com.example.demosecurityfilterchain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/home").permitAll()
                .requestMatchers("/about").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/home")
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails ana = User.builder()
                .username("ana")
                .password(passwordEncoder().encode("123"))
                .roles("ADMIN")// ROLE_STUDENT
                .build();
        UserDetails linda = User.builder()
                .username("linda")
                .password(passwordEncoder().encode("123"))
                .roles("USER")
                .build();
        UserDetails tom = User.builder()
                .username("tom")
                .password(passwordEncoder().encode("123123"))
                .roles("ADMIN")
                .build();// ROLE_STUDENT
        return new InMemoryUserDetailsManager(
                ana,
                linda,
                tom
        );
    }
}
