package com.example.signinandup.friendship;

import com.example.signinandup.user.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "api/v1/friendship")
@AllArgsConstructor
public class FriendshipController {
    @Autowired
    private FriendshipService service;

    @PostMapping
    public boolean addFriend(long firstUserId,long secondUserId) {
        return service.addFriend(firstUserId, secondUserId);
    }

    @GetMapping
    public List<User> getFriends(long userId) {
        return service.getFriends(userId);
    }

}
