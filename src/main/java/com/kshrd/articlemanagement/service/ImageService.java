package com.kshrd.articlemanagement.service;

import com.kshrd.articlemanagement.model.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image uploadImage(MultipartFile file) throws IOException;

    Resource getImageByName(String fileName) throws IOException;
}
