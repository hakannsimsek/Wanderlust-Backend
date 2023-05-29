package com.example.signinandup.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatDetailRepository extends JpaRepository<ChatDetail,Long> {

}
