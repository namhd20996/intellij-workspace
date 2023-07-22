package com.example.demosecurity.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static com.example.demosecurity.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/login/**", "/index", "/home-page")
                .permitAll()
                .antMatchers("/api/student/**").hasAnyRole(STUDENT.name(), ADMIN.name(), ADMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login") // khi có request /login nó sẽ chuyển đến controller có url là /login để trả về
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/courses", true)
                    .failureUrl("/login?incorrectAccount=error")
                    .loginProcessingUrl("/login-check")
                .and()
                .rememberMe() // Mặc định lưu 2 tuần
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                    .key("somethingverysecured")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login");
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails ana = User.builder()
                .username("ana")
                .password(passwordEncoder.encode("123"))
//                .roles(STUDENT.name()) // ROLE_STUDENT
                .authorities(STUDENT.getGrantedAuthorities())
                .build();
        UserDetails linda = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("123"))
//                .roles(ADMIN.name()) // ROLE_STUDENT
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        UserDetails tom = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("123"))
//                .roles(ADMINTRAINEE.name()) // ROLE_STUDENT
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(
                ana,
                linda,
                tom
        );
    }
}
