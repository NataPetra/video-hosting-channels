package by.nata.videohostingchannels.service;

import by.nata.videohostingchannels.controller.exception.ImageNotFoundException;
import by.nata.videohostingchannels.controller.exception.ImageStorageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    @Transactional
    public void storageImage(InputStream imageInputStream, String uuid) {
        String imagesFolderPath = getFolderPath();
        try {
            Path location = Paths.get(imagesFolderPath);
            if (!Files.exists(location)) {
                Files.createDirectories(location);
            }
            Path imageLocation = Paths.get(imagesFolderPath, uuid);
            Files.copy(imageInputStream, imageLocation);
        } catch (IOException e) {
            throw new ImageStorageException(e.getMessage());
        }
    }

    @Transactional
    public byte[] getImage(String imageName) throws IOException {
        String imagesFolderPath = getFolderPath();
        File image = Paths.get(imagesFolderPath, imageName).toFile();
        if (!image.exists()) {
            throw new ImageNotFoundException(String.format("Image %s not found", image.getName()));
        }
        try (FileInputStream inputStream = new FileInputStream(image)) {
            return StreamUtils.copyToByteArray(inputStream);
        }
    }

    private static String getFolderPath() {
        String rootPath = System.getProperty("user.dir");
        return rootPath + "/images/";
    }
}
