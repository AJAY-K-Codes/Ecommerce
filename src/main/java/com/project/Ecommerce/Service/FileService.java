package com.project.Ecommerce.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String UpdateImage(String path, MultipartFile file) throws IOException;
}
