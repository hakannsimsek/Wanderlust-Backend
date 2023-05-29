package com.example.signinandup.friendship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship,Long> {
    Optional<List<Friendship>> findByUserId(Long userId);
}
