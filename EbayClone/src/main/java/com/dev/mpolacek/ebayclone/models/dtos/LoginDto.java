package com.dev.mpolacek.ebayclone.models.dtos;

public class LoginDto {
    private String email;
    private String username;
    private String password;
    private String status;
    private String token;

    public LoginDto() {
    }

    public LoginDto(String email, String username, String password, String status, String token) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.status = status;
        this.token = token;
    }

    public LoginDto(String email, String username, String status, String jwt) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
