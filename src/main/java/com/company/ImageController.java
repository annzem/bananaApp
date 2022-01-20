package com.company;

import com.company.model.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.company.model.Image;
import org.springframework.web.server.ResponseStatusException;

@RestController
class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @PostMapping
    Long uploadImage(@RequestParam MultipartFile multipartImage) throws Exception {
        Image dbImage = new Image();
        dbImage.setName(multipartImage.getName());
        dbImage.setContent(multipartImage.getBytes());

        return imageRepository.save(dbImage)
                .getId();
    }

    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    ByteArrayResource downloadImage(@PathVariable Long imageId) {
        byte[] image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();

        return new ByteArrayResource(image);
    }
}