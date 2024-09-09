package com.kshrd.articlemanagement.service.serviceimpl;

import com.kshrd.articlemanagement.exception.BadRequestException;
import com.kshrd.articlemanagement.exception.NotFoundException;
import com.kshrd.articlemanagement.model.Image;
import com.kshrd.articlemanagement.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private static final Path PATH = Paths.get("src/main/resources/images");

    @Override
    public Image uploadImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        if (fileName.contains(".jpg") || fileName.contains(".png") || fileName.contains(".jpeg")
            || fileName.contains(".gif") || fileName.contains(".bmp") || fileName.contains(".tiff")) {
            fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(fileName);
            if (!Files.exists(PATH)) {
                Files.createDirectories(PATH);
            }
            Files.copy(file.getInputStream(), PATH.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            return new Image(fileName, file.getContentType(), file.getSize());
        }
        throw new BadRequestException("File must be contain jpg, png, jpeg, gif, bmp, tiff");
    }

    @Override
    public Resource getImageByName(String fileName) throws IOException {
        if (!(fileName.contains(".jpg") || fileName.contains(".png") || fileName.contains(".jpeg")
              || fileName.contains(".gif") || fileName.contains(".bmp") || fileName.contains(".tiff")
        )) {
            throw new BadRequestException("File must be contain jpg, png, jpeg, gif, bmp, tiff");
        }
        Path path = Paths.get("src/main/resources/images/" + fileName);
        if (Files.notExists(path)) {
            throw new NotFoundException("File not founded");
        }
        return new ByteArrayResource(Files.readAllBytes(path));
    }
}
