package com.ziti.chatback.repositories;

import com.ziti.chatback.entities.Conversation;
import com.ziti.chatback.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findAllByUsersUserId (int user_id);
}
