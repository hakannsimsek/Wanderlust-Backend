package com.example.signinandup.post.comment;

import com.example.signinandup.post.Post;
import com.example.signinandup.user.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "PostComments")
public class PostComment {
    @Id
    @SequenceGenerator(
            name = "post_comment_sequence",
            sequenceName = "post_comment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_comment_sequence"
    )
    private long id;

    @OneToOne(
            optional = false
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "post_id",
            referencedColumnName = "id"
    )
    private Post post;
    private String comment;
    private boolean isActive = true;
    private LocalDateTime time = LocalDateTime.now();
    private boolean modified;

    //TODO: Time, Modified

    public PostComment(User user, Post post, String comment) {
        this.user = user;
        this.post = post;
        this.comment = comment;
    }
}
