package com.example.signinandup.chat;

import org.springframework.stereotype.Service;

@Service
public class ChatRequest {
    String senderUserName;
    String receiverUsername;
    String message;
}
