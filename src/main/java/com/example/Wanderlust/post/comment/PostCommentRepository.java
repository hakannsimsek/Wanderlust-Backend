package com.example.signinandup.post.comment;

import com.example.signinandup.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {



}
