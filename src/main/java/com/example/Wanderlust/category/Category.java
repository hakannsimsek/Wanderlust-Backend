package com.example.signinandup.category;

import com.example.signinandup.post.Post;
import com.example.signinandup.post.comment.PostComment;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@ToString
@Table(name = "Categories")
public class Category {
    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_sequence"
    )
    private long id;
    private String name;
    @OneToOne(mappedBy = "category")
    private Post post;

    public Category(String name) {
        this.name = name;
    }
}
