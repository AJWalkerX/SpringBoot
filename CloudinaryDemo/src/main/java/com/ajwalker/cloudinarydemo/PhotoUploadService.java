package com.ajwalker.cloudinarydemo;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PhotoUploadService {
    private final Cloudinary cloudinary;



    public String uploadPhoto(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("url").toString();
    }

    public void savePhoto(String photoUrl) {
        Photo photo = Photo.builder()
                .url(photoUrl)
                .productId(0L)
                .build();
        photoRepository.save(photo);
    }
}
