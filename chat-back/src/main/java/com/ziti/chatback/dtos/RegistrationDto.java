package com.ziti.chatback.dtos;

public class RegistrationDto {
    private long id;
    private String username;
    public RegistrationDto(long id, String username) {
        this.username = username;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
