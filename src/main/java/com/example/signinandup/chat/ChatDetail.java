package com.example.signinandup.chat;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "ChatDetails")
public class ChatDetail {
    @Id
    @SequenceGenerator(
            name = "chat_detail_sequence",
            sequenceName = "chat_detail_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "chat_detail_sequence"
    )
    private long id;
    private String message;

    @ManyToOne
    @JoinColumn(
            name = "session_id",
            referencedColumnName = "id"
    )
    private ChatSession chatSession;

    public ChatDetail(ChatSession session,String message) {
        this.chatSession = session;
        this.message = message;
    }
}
