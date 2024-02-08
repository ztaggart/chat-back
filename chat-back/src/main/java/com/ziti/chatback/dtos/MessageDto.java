package com.ziti.chatback.dtos;

import java.util.Date;

public class MessageDto {
    private String message;

    private String from;

    private String time;

    private long id;

    public MessageDto() {

    }

    public MessageDto(String message, String from, String time, long id) {
        this.message = message;
        this.from = from;
        this.time = time;
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
