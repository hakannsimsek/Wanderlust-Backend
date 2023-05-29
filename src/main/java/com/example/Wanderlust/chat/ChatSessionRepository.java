package com.example.signinandup.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatSessionRepository extends JpaRepository<ChatSession,Long> {
    Optional<ChatSession> findBySenderId(Long id);
}
