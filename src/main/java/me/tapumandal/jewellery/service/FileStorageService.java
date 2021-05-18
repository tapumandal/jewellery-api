package me.tapumandal.jewellery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {

        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        //System.out.println(this.fileStorageLocation);

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
//            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
            //System.out.println("Could not create the directory where the uploaded files will be stored.");
        }
    }

    public String storeFile(MultipartFile file, String name) {

        String extension = getExtension(file);
        String newFileName = name+"."+extension;
        // Normalize file name
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = StringUtils.cleanPath(newFileName);

        try {
            if(fileName.contains("..")) {
                //System.out.println("Sorry! Filename contains invalid path sequence ");
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
//            //System.out.println("Path fileStorageLocation: "+ new Gson().toJson(fileStorageLocation));
//            System.out.println("Path targetLocation: "+ new Gson().toJson(targetLocation));
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            //System.out.println("Could not store file " + fileName + ". Please try again!");
        }
        return "";
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                //System.out.println("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            //System.out.println("File not found " + fileName);
        }
        return null;
    }

    private String getExtension(MultipartFile file) {
        int i = file.getOriginalFilename().lastIndexOf('.');
        if (i > 0) {
            return file.getOriginalFilename().substring(i+1);
        }
        return "";
    }
}
