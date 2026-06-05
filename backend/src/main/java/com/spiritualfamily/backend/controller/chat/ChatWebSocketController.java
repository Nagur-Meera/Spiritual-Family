package com.spiritualfamily.backend.controller.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.spiritualfamily.backend.dto.chat.ChatMessageDto;

@Controller
public class ChatWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatWebSocketController(
            SimpMessagingTemplate messagingTemplate
    ) {

        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/send")
    public void sendMessage(
            ChatMessageDto message
    ) {

        messagingTemplate.convertAndSend(
                "/topic/messages",
                message
        );
    }
}