package com.example.signinandup.post.comment;

import com.example.signinandup.friendship.FriendshipService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "api/v1/postcomment")
@AllArgsConstructor
public class PostCommentController {
    @Autowired
    private PostCommentService postCommentService;

    @PostMapping
    public boolean postComment(Long postId, String comment) {
        return postCommentService.postComment(postId,comment);
    }
}
