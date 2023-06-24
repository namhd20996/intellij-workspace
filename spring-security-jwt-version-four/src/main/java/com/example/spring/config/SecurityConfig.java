package com.example.spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
//                .regexMatchers("/api/v1/auth/register", "/api/v1/auth/authenticate")
                .antMatchers("/api/v1/auth/register", "/api/v1/auth/authenticate")
                .permitAll()
                /*.antMatchers("/api/v1/manager/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())
                .antMatchers(GET, "/api/v1/manager/**").hasAnyAuthority(ADMIN_READ.getPermission(), MANAGER_READ.getPermission())
                .antMatchers(POST, "/api/v1/manager/**").hasAnyAuthority(ADMIN_CREATE.getPermission(), MANAGER_CREATE.getPermission())
                .antMatchers(PUT, "/api/v1/manager/**").hasAnyAuthority(ADMIN_UPDATE.getPermission(), MANAGER_UPDATE.getPermission())
                .antMatchers(DELETE, "/api/v1/manager/**").hasAnyAuthority(ADMIN_DELETE.getPermission(), MANAGER_DELETE.getPermission())
                .antMatchers("/api/v1/admin/**").hasAnyRole(Role.ADMIN.name(), Role.ADMIN_FAKE.name())*/
                /*.antMatchers(GET, "/api/v1/admin/**").hasAnyAuthority(ADMIN_READ.getPermission())
                .antMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.getPermission())
                .antMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.getPermission())
                .antMatchers(DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.getPermission())*/
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        // UsernamePasswordAuthenticationFilter là filter sẽ đảm nhận việc authentication trong Spring Security và mặc định thông tin username và password của user sẽ được sử dụng cho quá trình authentication này
        // Khi sử dụng UsernamePasswordAuthenticationFilter có thể không cần đăng nhập mà vẫn có thể sử dụng được các chức năng khi đã đăng nhập ví dụ TOKEN
        return http.build();
    }
}
