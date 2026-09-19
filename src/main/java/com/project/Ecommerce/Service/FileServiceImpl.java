package com.project.Ecommerce.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Override
    public String UpdateImage(String path, MultipartFile file) throws IOException {
        //File name of Current Image
        String Originalname = file.getOriginalFilename();
        //Generate a Randomfilename
        String randomid= UUID.randomUUID().toString();
        String fileName=randomid.concat(Originalname.substring(Originalname.lastIndexOf('.')));

        Path uploadPath = Paths.get(path);

        // Create images folder if it doesn't exist
        Files.createDirectories(uploadPath);

        // Complete file path
        Path FilePath = uploadPath.resolve(fileName);

        // Save uploaded file
        Files.copy(
                file.getInputStream(),
                FilePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        return fileName;
    }
}
