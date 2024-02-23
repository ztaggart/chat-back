package com.ziti.chatback.dtos;

public class ConvUserDto {
    private String username;
    public ConvUserDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
