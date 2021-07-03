package me.tapumandal.jewellery.domain.product;

import me.tapumandal.jewellery.entity.ImageModel;
import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.util.ImageServiceUtil;
import me.tapumandal.jewellery.util.MyPagenation;
import me.tapumandal.jewellery.util.ResourceVerifier;
import me.tapumandal.jewellery.domain.image.ImageService;
import me.tapumandal.jewellery.domain.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ResourceVerifier resourceVerifier;

    @Autowired
    ImageServiceUtil imageServiceUtil;



    @Autowired
    ImageService imageService;

    String PRODUCT_FILE_PATH = "public/images/product/";

    private Product product;

    public ProductServiceImpl(){}

    public ProductServiceImpl(Product product){
        this.product = product;
    }

    @Override
    public Product create(ProductDto productDto) {

        PRODUCT_FILE_PATH = System.getProperty("user.dir")+PRODUCT_FILE_PATH;

        Product pro = new Product(productDto);

        if(productDto.getImages() != null) {
            List<Image> productImages = storeProductImages(productDto.getImages());
            pro.setProductImages(productImages);
        }

//        String thumbnailUrl = storeThumbnail(productDto.getThumbnailImg());
        ImageModel thumbnailImgModel = imageServiceUtil.createThumbnail(productDto.getThumbnailImg());
        pro.setImage(thumbnailImgModel.getUrl());
        pro.setImageName(thumbnailImgModel.getName());

        Optional<Product> product;

//        try{
            int productId = productRepository.create(pro);
            product = Optional.ofNullable(productRepository.getById(productId));
//        }catch (Exception e){
//            return null;
//        }

        if(product.isPresent()){
            return product.get();
        }else{
            return null;
        }
    }


    @Override
    public Product update(ProductDto productDto) {


        Product pro = new Product(productDto);

        product = getProductByID(pro.getId());
        if(product == null) {
            return null;
        }

//        List<Image> productImages = new ArrayList<Image>();
        List<Image> productImages = imageService.getImageByProductId(pro.getId());
        if(productDto.getImages() != null) {
//            productImages = storeProductImages(productDto.getImages());
//            productImages.addAll(existingImages);
            productImages.addAll(storeProductImages(productDto.getImages()));
        }
        pro.setProductImages(productImages);



        if(productDto.getThumbnailImg() != null) {
//            String thumbnailUrl = storeThumbnail(productDto.getThumbnailImg());
//            pro.setImage(thumbnailUrl);
            ImageModel thumbnailImgModel = imageServiceUtil.createThumbnail(productDto.getThumbnailImg());
            pro.setImage(thumbnailImgModel.getUrl());
            pro.setImageName(thumbnailImgModel.getName());
        }else{
            pro.setImage(product.getImage());
        }


        int proId = productRepository.update(pro);
        product = getProductByID(pro.getId());

        return product;
    }

    private Product getProductByID(int id) {
        Optional<Product> product;
        try{
            product = Optional.ofNullable(productRepository.getById(id));
        }catch (Exception e){
            return null;
        }
        if(product.isPresent()){
            return product.get();
        }else{
            return null;
        }
    }

    @Override
    public List<Product> getAll(Pageable pageable, ListFilter listFilter) {
        Optional<List<Product>> products = Optional.ofNullable(productRepository.getAll(pageable, listFilter));

        if(products.isPresent()){
            return products.get();
        }else{
            return null;
        }
    }

    @Override
    public Product getById(int id) {

        Optional<Product> product = Optional.ofNullable(productRepository.getById(id));

        if(product.isPresent()){
            return product.get();
        }else{
            return null;
        }
    }

    @Override
    public Product getByName(String name) {

        Optional<Product> product = Optional.ofNullable(productRepository.getByName(name));

        if(product.isPresent()){
            return product.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return productRepository.delete(id);
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public Product getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Product> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        Optional<Product> product = Optional.ofNullable(productRepository.getById(id));
        if(product.isPresent()){
            if(product.get().isActive()){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return product.isDeleted();
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return productRepository.getPageable(pageable);
    }

    @Override
    public int getAllEntityCount(Pageable pageable, ListFilter listFilter) {
        return productRepository.getAllEntityCount(pageable, listFilter);
    }


    @Override
    public List<ProductBusiness> getAllBusiness(Pageable pageable, String flag, String selectedParentMenu) {

        //System.out.println("FLAG: "+flag);
        //System.out.println("PARENT: "+selectedParentMenu);
        flag = flag.replace(",", "%");
        flag = flag.replace("&", "%");
        flag = flag.replace("and", "%");
        flag = flag.replace("And", "%");
        flag = flag.replace("AND", "%");

        selectedParentMenu = selectedParentMenu.trim();

        Optional<List<ProductBusiness>> products = Optional.ofNullable(productRepository.getAllBusiness(pageable, flag, selectedParentMenu));

        if(products.isPresent()){
            return products.get();
        }else{
            return null;
        }

    }

    @Override
    public int getAllBusinessEntityCount(Pageable pageable, String flag) {
        return productRepository.getAllBusinessEntityCount(pageable, flag);
    }

    @Override
    public List<ProductBusiness> searchProduct(Pageable pageable, String query) {
//        System.out.println("===========================================");
//        System.out.println("ORIGINAL: "+query);
//        String[] arrayQuery = query.split(" ");
//        List<String> queries = Arrays.asList(arrayQuery);
//        Collections.reverse(queries);
//        query = "";
//        for(int i =0 ; i<arrayQuery.length; i++){
//            query = query+arrayQuery[i]+" ";
//        }
//        for(int i =0 ; i<queries.size(); i++){
//            query = query+queries.get(i)+" ";
//        }
//        query.trim();
//
//        System.out.println("REVERSED: "+query);

        query = query.replace(",", "%");
        query = query.replace("&", "%");
        query = query.replace("and", "%");
        query = query.replace("And", "%");
        query = query.replace("AND", "%");
        query = query.replace(" ", "%");


        Optional<List<ProductBusiness>> products = Optional.ofNullable(productRepository.searchProduct(pageable, query));

        if(products.isPresent()){
            return products.get();
        }else{
            return null;
        }
    }

    @Override
    public int searchProductEntityCount(Pageable pageable, String query) {
        return productRepository.searchProductEntityCount(pageable, query);
    }


//    private String storeThumbnail(MultipartFile thumbnail) {
//        ImageModel imageModel = imageServiceUtil.createThumbnail(thumbnail);
//        return imageModel.getUrl();
//    }

    public List<Image> storeProductImages(MultipartFile[] images) {

        List<ImageModel> imageModels = imageServiceUtil.store(images);

        List<Image> productImages = new ArrayList<>();

        for (ImageModel tmp: imageModels) {
            Image image = new Image();
            image.setName(tmp.getName());
            image.setUrl(tmp.getUrl());
            image.setSize(tmp.getSize());

            productImages.add(image);
        }

        return productImages;
    }
}
