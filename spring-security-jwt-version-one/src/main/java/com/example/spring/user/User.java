package com.example.spring.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstname")
    private String firstname;

    private String lastname;

    private String email;

    private String password;

    // khai báo cho spring biết sử dụng enum để làm param và EnumType.STRING để lấy ra giá trị của enum
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    //    time tài khoản đã hết hạn hay chưa
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // tài khoản có bị khóa hay không
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //    thông tin đăng nhập có hết hạn hay không
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //    xác định xác thông tin người dùng
    @Override
    public boolean isEnabled() {
        return true;
    }
}
