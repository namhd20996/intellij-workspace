package com.example.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SercurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Function này giúp quản lý xác user và gán quyền cho user
        // .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").authorities("ACCESS_API-ONE", "ACCESS_API-TWO") phân quyền dạng chi tiết
        // nếu 1 user bình thường nếu biết quản lí mình có thể cấp quyền riêng cho 1 user đó để có thể truy cập vào đường dẫn đó
        // Khi đó cần phân quyền chi tiết còn nếu không phân quyền chi tiết thì sẽ hạn chế về mặt phân quyền
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").authorities("ACCESS_API-ONE", "ACCESS_API-TWO")
                .and()
                .withUser("nam").password(passwordEncoder().encode("123")).roles("USER")
                .and()
                .withUser("manager").password(passwordEncoder().encode("123")).roles("MANAGER").authorities("ACCESS_API-ONE");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Thực hiện quét các request để phân quền
        // authorizeRequests() nhận biết để phân quyền các request
        //.anyRequest().authenticated() mọi request phải đăng nhập
        //.permitAll() mọi người đều có thể truy cập
        // Lưu ý thứ tự phân quyền nó sẽ quét lần lượt nên cần sắp xếp sao cho hợp lí
        http
                .authorizeRequests()
//                .anyRequest().authenticated()
                .antMatchers("/home-page").permitAll()
                .antMatchers("/user/**").authenticated() // phải đăng nhập
//                .antMatchers("/admin").hasRole("ADMIN") // Khi sử dụng đường dẫn cứng
                .antMatchers("/admin/**").hasRole("ADMIN") // quét toàn bộ các đường dẫn có url bắt đầu là /admin
                .antMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
//                .antMatchers("/api/**").authenticated()
                .antMatchers("/api/api-one").hasAuthority("ACCESS_API-ONE")
                .antMatchers("/api/api-two").hasAuthority("ACCESS_API-TWO")
//                .antMatchers("/api/api-two").authenticated()
//                .antMatchers("/api/api-three").hasRole("MANAGER")
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
