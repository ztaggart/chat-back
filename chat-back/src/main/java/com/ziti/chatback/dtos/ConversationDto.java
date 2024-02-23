package com.ziti.chatback.dtos;

import com.ziti.chatback.entities.Conversation;
import com.ziti.chatback.entities.Message;

import java.util.List;
import java.util.Set;

public class ConversationDto {
    private Long id;
    private List<ConvUserDto> users;
    private Set<Message> messages;
    public ConversationDto(Conversation conversation) {
        this.id = conversation.getId();
        this.users = conversation.getUsers().stream().map((user) -> new ConvUserDto(user.getUsername())).toList();
        this.messages = conversation.getMessages();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ConvUserDto> getUsers() {
        return users;
    }

    public void setUsers(List<ConvUserDto> users) {
        this.users = users;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
