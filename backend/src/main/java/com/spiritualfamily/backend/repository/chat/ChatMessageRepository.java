package com.spiritualfamily.backend.repository.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.chat.ChatMessage;

public interface ChatMessageRepository
        extends JpaRepository<ChatMessage, Long> {
}