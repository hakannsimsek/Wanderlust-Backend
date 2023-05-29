package com.example.signinandup.post.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface PostFileRepository extends JpaRepository<PostFile,Long> {
    Optional<PostFile> findByName(String name);
    Optional<PostFile> findPostFileById(Long id);
}
