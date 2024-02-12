package com.ziti.chatback.services;

import com.ziti.chatback.entities.Conversation;
import com.ziti.chatback.repositories.ConversationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ConversationService {
    private final ConversationRepository conversationRepository;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public List<Conversation> getAllConversations(int userId) {
        return this.conversationRepository.findAllByUsersUserId(userId);
    }

}
