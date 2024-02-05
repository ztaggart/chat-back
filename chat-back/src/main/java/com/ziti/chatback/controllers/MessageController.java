package com.ziti.chatback.controllers;

import com.ziti.chatback.entities.User;
import com.ziti.chatback.repositories.MessageRepository;
import com.ziti.chatback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.ziti.chatback.dtos.MessageDto;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class MessageController {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/messages/{step}", params = "userId")
    public List<MessageDto> getAllMessagesForId(@RequestParam Optional<Integer> userId, @PathVariable String step) {
        if (userId.isPresent()) {
            User user = new User();
            user.setUserId(userId.get());
            return messageRepository.findMessagesByUser(user)
                    .stream()
                    .map(message -> new MessageDto(message.getMessage(), message.getUser().getUsername(), message.getTime().toString(), message.getId()))
                    .toList();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A userId must be present.");
        }
    }

    @GetMapping("/messages")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<MessageDto> getAllMessages() {
        return messageRepository.findAll().stream()
                .map(message -> new MessageDto(message.getMessage(), message.getUser().getUsername(), message.getTime().toString(), message.getId())).toList();
    }

//    @PostMapping("/message")
//    public MessageDto sendMessage(@RequestBody Optional<MessageDto> messageDto) {
//        if (messageDto.isPresent()) {
//            User user = userRepository.findUserByUsername(messageDto.get().getFrom())
//                    .orElseThrow(() ->
//                            new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                                    "The given username was not found."));
//            Message message = new Message();
//            message.setMessage(messageDto.get().getMessage());
//            message.setUser(user);
//            Message savedMessage = messageRepository.save(message);
//            return new MessageDto(savedMessage.getMessage(), savedMessage.getUser().getUsername());
//        }
//        else {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed message.");
//        }
//    }
}
