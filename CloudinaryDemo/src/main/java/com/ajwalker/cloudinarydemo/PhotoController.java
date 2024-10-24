package com.ajwalker.cloudinarydemo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("photo")
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoUploadService photoUploadService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        String photoUrl = photoUploadService.uploadPhoto(file);
        return ResponseEntity.ok("Photo uploaded successfully: " + photoUrl);
    }
}
