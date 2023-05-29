package com.example.signinandup.post.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class PostFileService {
    @Autowired
    private PostFileRepository repository;

    public String uploadImage (MultipartFile file) throws IOException {
        PostFile postFile = repository.save(PostFile.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(PostFileUtils.compressImage(file.getBytes())).build());
        if (postFile != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName) {
        PostFile dbImageData = repository.findByName(fileName).orElseThrow();
        byte[] images = PostFileUtils.decompressImage(dbImageData.getImageData());
        return images;
    }

}
