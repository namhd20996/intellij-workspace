package com.example.spring.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Tạo chữ ký
    private static final String SECRET_KEY = "6KGMeQrk6AGC6xJMBeqWwkY/udp6ALKljw5RTHevYlhutlKMrG4/qnIsaq5G46BJ";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // trích xuất kiểm tra 1 user
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Tạo 1 chuỗi toke từ thông tin user
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // Kiểm tra thời gian của token còn hiệu lực hay không
    private boolean isTokenExpired(String token) {
        // lấy ra ngày hết hạn của token rồi so sánh trước ngày hôm nay sử dụng
        // before(new Date())
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) // lấy ra tên tài khoản user để mã hóa
                .setIssuedAt(new Date(System.currentTimeMillis())) // set time bắt đầu hiệu lực của token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // mã hóa chữ ký theo định dạng HS256
                .compact();
    }

    // Dùng để xác nhận quyền đối với toàn bộ user
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey()) // đặt khóa ký tự - chữ ký của token
                .build()
                .parseClaimsJws(token)  // phương thức xác nhận quyền
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // giải mã hóa
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
