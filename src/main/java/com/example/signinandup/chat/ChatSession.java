package com.example.signinandup.chat;

import com.example.signinandup.user.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "ChatSessions")
public class ChatSession {
    @Id
    @SequenceGenerator(
            name = "chat_session_sequence",
            sequenceName = "chat_session_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "chat_session_sequence"
    )
    private long id;

    @OneToOne
    @JoinColumn(
            name = "sender_id",
            referencedColumnName = "id"
    )
    private User sender;

    @OneToOne
    @JoinColumn(
            name = "receiver_id",
            referencedColumnName = "id"
    )
    private User receiver;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "chatSession"
    )
    private List<ChatDetail> chatDetail;

    public ChatSession(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }
}
