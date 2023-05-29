package com.example.signinandup.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;


    public boolean generatePost(Post post) {
        repository.save(post);
        return true;
    }

    //TODO: GetFriendsPosts
    public void getFriendsPosts(Long userId) {

    }
}
