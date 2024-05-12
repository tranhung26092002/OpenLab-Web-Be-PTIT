package com.example.webtoeic.DTO;

public class AuthenticationResponse {
    private String token;
    private String firebaseToken;
    private int userId;
    private String role;
    private String email;


    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String token, String firebaseToken, int userId, String role, String email) {
        this.token = token;
        this.firebaseToken = firebaseToken;
        this.userId = userId;
        this.role = role;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
