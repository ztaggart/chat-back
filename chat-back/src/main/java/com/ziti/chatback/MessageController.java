package com.ziti.chatback;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public ResponseMessage getMessage(Message message) {
        System.out.println(message.getMessageContent());

        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
    }
}
