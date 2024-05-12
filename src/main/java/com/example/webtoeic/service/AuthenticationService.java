package com.example.webtoeic.service;

import com.example.webtoeic.DTO.AuthenticationResponse;
import com.example.webtoeic.entity.User;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.repository.UserRepositoty;
import com.google.firebase.auth.FirebaseAuth;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.*;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepositoty userRepositoty;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Đoạn JWT_SECRET là bí mật, chỉ có phía server biết
    @Value("${jwt.secret-key}")
    private String JWT_SECRET_STRING;

    // method sử dụng key mở khóa
    private Key getSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(JWT_SECRET_STRING);
        return Keys.hmacShaKeyFor(decodedKey);
    }
    //    private final Key JWT_SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    // Thời gian có hiêu lực của jwt (10 ngày)
    private final long JWT_EXPIRATION = 864000000L;

    public AuthenticationResponse login(String email, String password) {
        User user = userRepositoty.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Thong tin dang nhap dung tao ra jwt token
            String token = createToken(user);

            String firebaseToken = createFirebaseCustomToken(String.valueOf(user.getId()));

            String role = mapRoles(user.getVaiTro()).get(0);
            System.out.println("role: " + role);
            System.out.println("userId: " + user.getId());
            // trả về email
            AuthenticationResponse response = new AuthenticationResponse(token, firebaseToken, user.getId(), role, email);
            return response;
        }
        throw new BadCredentialsException("Invalid email or password");
    }

    public User register(String email, String password, int vaiTro) throws IllegalAccessException {
        //Kiểm tra xem email tồn tại không
        Optional<User> existingUser = Optional.ofNullable(userRepositoty.findByEmail(email));
        if(existingUser.isPresent()){
            throw new IllegalAccessException("Email already in use");
        }

        // Ma hoa mat khau
        String encryptedPassword = passwordEncoder.encode(password);

        // Tao nguoi dung moi va luu vao co so du lieu
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(encryptedPassword);
        newUser.setVaiTro(vaiTro);

        return userRepositoty.save(newUser);
    }

    public User updateUser(int userId, String newEmail, String newPassword, Integer newVaiTro){
        User user = userRepositoty.findById(userId)
                .orElseThrow(()->new NoSuchElementException("User not found with id: " + userId));

        if (newEmail != null && !newEmail.isEmpty()) {
            user.setEmail(newEmail);
        }
        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(passwordEncoder.encode(newPassword));
        }
        if (newVaiTro != null) {
            user.setVaiTro(newVaiTro);
        }
        return userRepositoty.save(user);
    }

    public BaseResponse deleteUser(int userId){
        userRepositoty.deleteById(userId);
        return null;
    }

    public List<User> getAllUsers(){
        return userRepositoty.findAll();
    }

    private String createToken(User user) {
        // Thời gian hết hạn của Token
        Date expiryDate = new Date(System.currentTimeMillis() + JWT_EXPIRATION);
        //Tạo chuỗi json web token từ id của user
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("roles",mapRoles(user.getVaiTro()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(getSecretKey())
                .compact();
    }

    public List<String> mapRoles(int vaiTro) {
        List<String> roles = new ArrayList<>();
        switch (vaiTro){
            case 1:
                roles.add("ROLE_USER");
                break;
            case 2:
                roles.add("ROLE_ADMIN");
                break;
        }
        return roles;
    }

    public UserDetails verifyToken(String token){
        try {
            // Giải mã token
            String email = Jwts.parserBuilder()
                    .setSigningKey(getSecretKey()).build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            // trả về email từ token
            User user = userRepositoty.findByEmail(email);
            // Trả về một đối tượng UserDetails
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (String role : mapRoles(user.getVaiTro())) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        } catch (Exception e){
            // Nếu token không hợp lệ, hoặc hết hạn trả về null
            return null;
        }
    }

    // Phương thức tạo custom token
    private String createFirebaseCustomToken(String userId){
        try {
            String customToken = FirebaseAuth.getInstance().createCustomToken(userId);
            return customToken;
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}