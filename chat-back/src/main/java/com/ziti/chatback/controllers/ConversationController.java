package com.ziti.chatback.controllers;

import com.ziti.chatback.entities.Conversation;
import com.ziti.chatback.services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ConversationController {

    private final ConversationService conversationService;

    @Autowired
    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping(value = "/conversations/{userId}")
    public List<Conversation> getAllConversationsForUser(@PathVariable int userId) {
        return conversationService.getAllConversations(userId);
    }
}
