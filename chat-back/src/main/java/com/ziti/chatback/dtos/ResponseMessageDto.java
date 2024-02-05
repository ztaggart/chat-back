package com.ziti.chatback.dtos;

@Deprecated
public class ResponseMessageDto {
    private String message;
    private String from;

    public ResponseMessageDto(String content, String from) {
        this.message = content;
        this.from = from;
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
}
