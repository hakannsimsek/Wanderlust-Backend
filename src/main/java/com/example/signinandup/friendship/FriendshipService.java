package com.example.signinandup.friendship;

import com.example.signinandup.user.User;
import com.example.signinandup.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipService {
    @Autowired
    private UserRepository repository;

    public boolean addFriend(long followerId,long followedId) {
        User user1 = repository.findById(followerId).orElseThrow();
        User user2 = repository.findById(followedId).orElseThrow();

        user1.addFriend(user2);
        repository.save(user2);
        return true;
    }

    public List<User> getFriends(long userId){
        User user = repository.findById(userId).orElseThrow();
        return user.getFriends();
    }


}
