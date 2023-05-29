package com.example.signinandup.post.file;

import com.example.signinandup.post.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PostFiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @ManyToOne
    @JoinColumn(
            name = "post_id",
            referencedColumnName = "id"
    )
    private Post post;

    @Lob
    @Column(
            name = "imagedata",
            length = 1000
            //columnDefinition="BLOB"
            )
    private byte[] imageData;
}