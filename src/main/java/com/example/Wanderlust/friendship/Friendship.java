package com.example.signinandup.friendship;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@ToString
//@Table(name = "Friendship")
public class Friendship {
    @Id
    @SequenceGenerator(
            name = "friendship_sequence",
            sequenceName = "friendship_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "friendship_sequence"
    )
    private long id;
    private long userId;
    private long friendId;
}
