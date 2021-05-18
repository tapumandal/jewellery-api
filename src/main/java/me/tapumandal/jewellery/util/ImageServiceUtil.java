package me.tapumandal.jewellery.util;

import me.tapumandal.jewellery.entity.ImageModel;
import me.tapumandal.jewellery.service.FileStorageService;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceUtil {


    @Autowired
    private FileStorageService fileStorageService;

    @Value("${base.path}")
    String basePath;

    @Value("${storage.path}")
    String storagePath;

    @Value("${file.upload-dir}")
    String productFileUploadDir;


    public List<ImageModel> store(MultipartFile[] images){
        List<ImageModel> imageModels = new ArrayList<>();

        int i=0;
        for (MultipartFile file: images) {
            ImageModel tmp = this.store(file);
            imageModels.add(tmp);
            i++;
        }
        return imageModels;
    }

    public ImageModel createThumbnail(MultipartFile file) {
        String thumbnailName = "thumbnail_"+String.valueOf(Instant.now().getEpochSecond());
        thumbnailName = fileStorageService.storeFile(file, thumbnailName);

        if(file.getSize()>10000) {
            try {
                Thumbnails.of(new File(productFileUploadDir+"/"+thumbnailName))
                        .outputFormat("JPEG")
                        .size(180, 180)
                        .crop(Positions.CENTER)
                        .keepAspectRatio(true)
                        .outputQuality(0.5)
                        .toFiles(Rename.NO_CHANGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return createImageModel(thumbnailName);
    }

    public ImageModel store(MultipartFile file){
        String fileName = String.valueOf(Instant.now().getEpochSecond());
        fileName = fileStorageService.storeFile(file, fileName);

        return createImageModel(fileName);
    }

    private ImageModel createImageModel(String fileName){
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path(basePath+storagePath)
//                .path(fileName)
//                .toUriString();

        ImageModel imageModel = new ImageModel();
        imageModel.setUrl(basePath+storagePath+fileName);
        imageModel.setName(fileName);

        return imageModel;
    }
}
