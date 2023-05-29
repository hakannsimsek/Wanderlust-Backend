package com.example.signinandup.post;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "api/v1/post")
@AllArgsConstructor
public class PostController {
    @Autowired
    private PostService service;

    @PostMapping
    public boolean generatePost(Post post){
        return service.generatePost(post);
    }

}
