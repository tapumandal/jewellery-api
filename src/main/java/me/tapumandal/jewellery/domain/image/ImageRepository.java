package me.tapumandal.jewellery.domain.image;

import org.hibernate.Session;

import java.util.List;

public interface ImageRepository{

    public Session getSession();

    public Image getImageByName(String name);

    public boolean delete(String name);

    public List<Image> getImageByProductId(int productId);
}
