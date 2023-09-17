package com.ziti.chatback;

public class ResponseMessage {
    private String content;

    public ResponseMessage() {

    }

    public ResponseMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
