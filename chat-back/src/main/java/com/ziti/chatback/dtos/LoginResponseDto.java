package com.ziti.chatback.dtos;

public class LoginResponseDto {
    private String username;
    private String jwt;
    private int userId;

    public LoginResponseDto(String username, String jwt, int userId) {
        this.username = username;
        this.jwt = jwt;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
