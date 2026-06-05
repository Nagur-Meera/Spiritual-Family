package com.spiritualfamily.backend.service.chat;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spiritualfamily.backend.dto.chat.ChatMessageRequest;
import com.spiritualfamily.backend.entity.chat.ChatMessage;
import com.spiritualfamily.backend.entity.user.User;
import com.spiritualfamily.backend.repository.chat.ChatMessageRepository;
import com.spiritualfamily.backend.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    private final UserRepository userRepository;

    public ChatMessage sendMessage(
            ChatMessageRequest request
    ) {

        User sender =
                userRepository.findById(
                        request.getSenderId()
                ).orElseThrow();

        ChatMessage message =
                ChatMessage.builder()
                        .message(request.getMessage())
                        .sender(sender)
                        .build();

        return chatMessageRepository.save(message);
    }

    public List<ChatMessage> getMessages() {

        return chatMessageRepository.findAll();
    }

    public void deleteMessage(Long id) {

        chatMessageRepository.deleteById(id);
    }
}