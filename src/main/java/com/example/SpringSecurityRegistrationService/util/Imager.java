package com.example.SpringSecurityRegistrationService.util;

import com.example.SpringSecurityRegistrationService.model.Person;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class Imager {
    @Value("${upload.path}")
    private String uploadPath;
    private static void resizeImage(String originalFilePath, String targetFilePath, int targetSize) {
        try {
            File sourceFile = new File(originalFilePath);
            BufferedImage originalImage = ImageIO.read(sourceFile);

            BufferedImage resizedImage = Scalr.resize(originalImage, targetSize);

            File resizedFile = new File(targetFilePath);
            ImageIO.write(resizedImage, "jpg", resizedFile);

            originalImage.flush();
            resizedImage.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setProfilePicture(Person person, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        if (!file.isEmpty()) {

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName =uuidFile + "." + fileName;


            file.transferTo(new File(uploadPath+"/"+resultFileName));
            Imager.resizeImage(uploadPath+"/"+resultFileName,uploadPath+"/"+resultFileName,180);


            person.setProfilePicDir(resultFileName);
        }
        else {
            person.setProfilePicDir("default.png");
        }
    }
}
