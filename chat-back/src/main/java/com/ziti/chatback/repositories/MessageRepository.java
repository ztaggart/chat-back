package com.ziti.chatback.repositories;

import com.ziti.chatback.entities.Message;
import com.ziti.chatback.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findMessagesByUser(User user);
}
