package com.example.signinandup.chat;

import com.example.signinandup.user.User;
import com.example.signinandup.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChatSessionRepository chatSessionRepository;
    @Autowired
    ChatDetailRepository chatDetailRepository;

    public void startChat(ChatRequest request) {
        User sender = userRepository.findByUsername(request.senderUserName).orElseThrow();
        User receiver = userRepository.findByUsername(request.receiverUsername).orElseThrow();

        ChatSession session = startChatSession(sender,receiver);

        startChatDetail(session, request.message);
    }

    public ChatSession startChatSession(User sender,User receiver) {
        ChatSession session;
        if (chatSessionRepository.findBySenderId(sender.getId()).isPresent()) {
            session = chatSessionRepository.findBySenderId(sender.getId()).orElseThrow();
        } else if (chatSessionRepository.findBySenderId(receiver.getId()).isPresent()) {
            session = chatSessionRepository.findBySenderId(receiver.getId()).orElseThrow();
        } else {
            session = new ChatSession(sender, receiver);
        }
        chatSessionRepository.save(session);

        return session;
    }

    public void startChatDetail(ChatSession session,String message) {
        ChatDetail chatDetail = new ChatDetail(session, message);

        chatDetailRepository.save(chatDetail);
    }

}
