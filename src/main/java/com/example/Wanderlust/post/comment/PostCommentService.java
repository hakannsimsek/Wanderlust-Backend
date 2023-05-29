package com.example.signinandup.post.comment;

import com.example.signinandup.post.Post;
import com.example.signinandup.post.PostRepository;
import com.example.signinandup.user.User;
import com.example.signinandup.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostCommentService {
    private PostRepository postRepository;
    private PostCommentRepository repository;
    private UserRepository userRepository;
    private final String POST_NOT_EXIST = "Post with %s id is not exist";

    //TODO: Post -> Long postID
    public boolean postComment(Long postId, String comment) {
        boolean isPostExist = postRepository.findPostById(postId).isPresent();
        if (!isPostExist)
            throw new IllegalStateException(String.format(POST_NOT_EXIST, postId));

        Post postToComment = postRepository.findPostById(postId).orElseThrow();

        User user = userRepository.findById(postToComment.getUser().getId()).orElseThrow();

        PostComment postComment = new PostComment(user, postToComment, comment);
        repository.save(postComment);
        return true;
    }

}
