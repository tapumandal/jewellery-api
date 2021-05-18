package me.tapumandal.jewellery.domain.image;

import java.util.List;

public interface ImageService {

    public Image getImageByName(String name);
    public boolean deleteImageByName(String name);
    public List<Image> getImageByProductId(int productId);
}
