package com.example.signinandup.post;

import com.example.signinandup.category.Category;
import com.example.signinandup.post.comment.PostComment;
import com.example.signinandup.post.file.PostFile;
import com.example.signinandup.user.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "Posts")
public class Post {
    @Id
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    private long id;

    @OneToOne(
            optional = false,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;
    private String title;
    private String description;
    private boolean isActive = true;
    private String location;
    private String tag;

    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL)
    private List<PostComment> comments;

    @OneToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    )
    private Category category;

    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL)
    private List<PostFile> files;
    //private LocalDateTime time;
    //private Integer like = 0;


    public Post(User user, String title, String description, String location, String tag, Category category) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.location = location;
        this.tag = tag;
        this.category = category;
    }


}
