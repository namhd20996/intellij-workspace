package com.example.lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
//                .authorizeHttpRequests(authConfig -> {
//                    authConfig.requestMatchers("/auth/login/**").permitAll();
//                    authConfig.requestMatchers("/home/index").permitAll();
//                    authConfig.requestMatchers("/home/admin").hasRole("ADMIN");
//                    authConfig.requestMatchers("/home/user").hasAnyRole("ADMIN", "USER");
//                    authConfig.requestMatchers("/auth/authenticated").authenticated();
//                    authConfig.requestMatchers("/home/about").hasRole("USER");
//                    authConfig.anyRequest().authenticated();
//                })
                .authorizeHttpRequests()
                .anyRequest().permitAll()
                .and()
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin()
                .loginPage("/auth/login/login-page")
                .loginProcessingUrl("/login-check")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home/index", false)
                .failureUrl("/auth/login/login-page?message=Login fail")
                .and()
                .logout()
                .logoutUrl("/logoff")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logoff", "GET"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/auth/login/login-page?message=Success");
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/auth/login/error");
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
                .roles("ADMIN", "GUEST")// ROLE_ADMIN
                .build();
        UserDetails linda = User.builder()
                .username("linda")
                .password(passwordEncoder().encode("123"))
                .roles("GUEST")
                .build();
        UserDetails tom = User.builder()
                .username("tom")
                .password(passwordEncoder().encode("123"))
                .roles("USER")
                .build();// ROLE_USER
        return new InMemoryUserDetailsManager(
                ana,
                linda,
                tom
        );
    }

}
