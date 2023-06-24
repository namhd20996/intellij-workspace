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
                .csrf().disable() // bật tính năng này lên giúp không xuất hiện đoạn mã XSRF-TOKEN trong cookie để bị người khác xâm nhập khi không cần password vẫn có thể sử dụng được
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/student/**").hasAnyRole(STUDENT.name(), ADMIN.name(), ADMINTRAINEE.name())
//                .antMatchers(HttpMethod.DELETE,"/api/manager/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST, "/api/manager/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/api/manager/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers("/api/manager/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/course", true)
                    .failureUrl("/login?error=true")
                .and()
                .sessionManagement()
                    .invalidSessionUrl("/login") // Hết time sẽ trả về url nào
                .and()
                .rememberMe()
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                    .key("somethingverysecured")
                    .rememberMeParameter("remember-me")
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me") // loại bỏ cookies đang được lưu trên web
                    .logoutSuccessUrl("/login")
               ;
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
