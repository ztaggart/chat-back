package com.ziti.chatback.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @Column(columnDefinition = "text")
    private String message;

    private Timestamp time;

    public Message() {

    }

    public Message(User user, String message) {
        this.user = user;
        this.message = message;
    }

    public Message(User user, String message, Conversation conversation, Timestamp time) {
        this.user = user;
        this.message = message;
        this.conversation = conversation;
        this.time = time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Conversation getConversation() {return this.conversation; }
    public void setConversation(Conversation conversation) {this.conversation = conversation;}
}
