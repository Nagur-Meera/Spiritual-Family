package com.spiritualfamily.backend.controller.chat;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spiritualfamily.backend.dto.chat.ChatMessageRequest;
import com.spiritualfamily.backend.entity.chat.ChatMessage;
import com.spiritualfamily.backend.service.chat.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService service;

    @PostMapping
    public ChatMessage sendMessage(
            @RequestBody ChatMessageRequest request
    ) {

        return service.sendMessage(request);
    }

    @GetMapping
    public List<ChatMessage> getMessages() {

        return service.getMessages();
    }

    @DeleteMapping("/{id}")
    public String deleteMessage(
            @PathVariable Long id
    ) {

        service.deleteMessage(id);

        return "Message Deleted";
    }

}