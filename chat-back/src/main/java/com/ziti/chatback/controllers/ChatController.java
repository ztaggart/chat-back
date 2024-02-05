package com.ziti.chatback.controllers;

import com.ziti.chatback.dtos.MessageDto;
import com.ziti.chatback.entities.Message;
import com.ziti.chatback.entities.User;
import com.ziti.chatback.repositories.MessageRepository;
import com.ziti.chatback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.HtmlUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

@Controller
public class ChatController {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public ChatController(UserRepository userRepository, MessageRepository messageRepository) {

        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @MessageMapping("/chat")
    @SendTo("/chatroom/public")
    public MessageDto getMessage(@RequestBody Optional<MessageDto> optionalMessageDto) {
        if (optionalMessageDto.isPresent()) {
            MessageDto messageDto = optionalMessageDto.get();
            User user = userRepository.findUserByUsername(messageDto.getFrom())
                    .orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                    "The given username was not found."));
            //2023-11-14T21:17:13.080Z
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
            Instant instant =
            LocalDateTime.parse(                   // Parse as an indeterminate `LocalDate`, devoid of time zone or offset-from-UTC. NOT a moment, NOT a point on the timeline.
                            messageDto.getTime(),        // This input uses a poor choice of format. Whenever possible, use standard ISO 8601 formats when exchanging date-time values as text. Conveniently, the java.time classes use the standard formats by default when parsing/generating strings.
                            formatter  // Use single-character `M` & `d` when the number lacks a leading padded zero for single-digit values.
                    )                                      // Returns a `LocalDateTime` object.
                    .atZone(                               // Apply a zone to that unzoned `LocalDateTime`, giving it meaning, determining a point on the timeline.
                            ZoneId.of("America/New_York")  // Always specify a proper time zone with `Contintent/Region` format, never a 3-4 letter pseudo-zone such as `PST`, `CST`, or `IST`.
                    )                                      // Returns a `ZonedDateTime`. `toString` → 2018-05-12T16:30-04:00[America/Toronto].
                    .toInstant();                           // Extract a `Instant` object, always in UTC by definition.
            Message message = new Message(user, messageDto.getMessage(), Timestamp.from(instant));
            Message savedMessage = messageRepository.save(message);
            return new MessageDto(HtmlUtils.htmlEscape(savedMessage.getMessage()), savedMessage.getUser().getUsername(), savedMessage.getTime().toString(), savedMessage.getId());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message malformed.");
        }

    }
}
