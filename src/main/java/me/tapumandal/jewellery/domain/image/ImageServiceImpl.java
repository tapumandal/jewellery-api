package me.tapumandal.jewellery.domain.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    private Image image;

    public ImageServiceImpl(){}

    public ImageServiceImpl(Image image){
        this.image = image;
    }

    @Override
    public Image getImageByName(String name) {
        Optional<Image> image = Optional.ofNullable(imageRepository.getImageByName(name));

        if(image.isPresent()){
            return image.get();
        }else{
            return null;
        }
    }

    @Override
    public List<Image> getImageByProductId(int productId) {
        List<Image> image = imageRepository.getImageByProductId(productId);
        return image;
    }

    @Override
    public boolean deleteImageByName(String name) {
        try {
            return imageRepository.delete(name);
        }catch (Exception ex){
            return false;
        }
    }
}
