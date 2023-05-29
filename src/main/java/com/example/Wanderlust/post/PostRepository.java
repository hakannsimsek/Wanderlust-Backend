package com.example.signinandup.post;

import com.example.signinandup.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findPostByUser(User user);

    Optional<Post> findPostsByLocation(String location);

    Optional<Post> findPostById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Post p SET p.isActive = false WHERE p.id = ?1")
    void disablePost(Long id);

}