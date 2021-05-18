package me.tapumandal.jewellery.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by TapuMandal on 11/7/2020.
 * For any query ask online.tapu@gmail.com
 */

public class ProductDto {

    @Nullable
    private int id;

    @NotNull
    @NotEmpty
    @Size(min=3, max = 50, message = "name field is not OK.")
    private String name;

    @Nullable
    private MultipartFile thumbnailImg;

    @Nullable
    private MultipartFile[] images;

    private String imageRealPath;

    @NotNull
    @NotEmpty
    @Size(min=3, max = 200, message = "categories field is not OK.")
    private String company;

    @NotNull
    @NotEmpty
    @Size(min=3, max = 200, message = "categories field is not OK.")
    private String categories;

    @NotNull
    @NotEmpty
    @Size(min=3, max = 200, message = "Pre selected categories field is not OK.")
    private String[] preSelectedCategories;

    @NotNull
    @NotEmpty
    @Size(min=5, max = 500, message = "description field is not OK.")
    private String description;

    @NotNull
    @NotEmpty
    @Size(min=1, max = 4, message = "buyingPricePerUnit field is not OK.")
    private float buyingPricePerUnit;

    @NotNull
    @NotEmpty
    @Size(min=1, max = 4, message = "sellingPricePerUnit field is not OK.")
    private int sellingPricePerUnit;

    @NotNull
    @NotEmpty
    @Size(min=1, max = 4, message = "discountPrice field is not OK.")
    private int discountPrice;

    @NotNull
    @NotEmpty
    @Size(min=2, max = 20, message = "discountTitle field is not OK.")
    private String discountTitle;

    @NotNull
    @NotEmpty
    @Size(min=1, max = 3, message = "unit field is not OK.")
    private float unit;

    @NotNull
    @NotEmpty
    @Size(min=1, max = 10, message = "unitTitle field is not OK.")
    private String unitTitle;

    @NotNull
    @NotEmpty
    @Size(min=1, max = 3, message = "quantity field is not OK.")
    private int quantity;

    @Size(min=1, max = 3, message = "quantity field is not OK.")
    private int maximumOrderQuantity = 20;

    private int sortPriority;

    private boolean active = true;

    private boolean isDelete = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getThumbnailImg() {
        return thumbnailImg;
    }

    public void setThumbnailImg(MultipartFile thumbnailImg) {
        this.thumbnailImg = thumbnailImg;
    }

    public MultipartFile[] getImages() {
        return images;
    }

    public void setImages(MultipartFile[] images) {
        this.images = images;
    }

    public String getImageRealPath() {
        return imageRealPath == null ? "" : imageRealPath;
    }

    public void setImageRealPath(String imageRealPath) {
        this.imageRealPath = imageRealPath;
    }

    public String getCompany() {
        return company == null ? "" : company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCategories() {
        return categories == null ? "" : categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String[] getPreSelectedCategories() {
        return preSelectedCategories;
    }

    public void setPreSelectedCategories(String[] preSelectedCategories) {
        this.preSelectedCategories = preSelectedCategories;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getBuyingPricePerUnit() {
        return buyingPricePerUnit;
    }

    public void setBuyingPricePerUnit(float buyingPricePerUnit) {
        this.buyingPricePerUnit = buyingPricePerUnit;
    }

    public int getSellingPricePerUnit() {
        return sellingPricePerUnit;
    }

    public void setSellingPricePerUnit(int sellingPricePerUnit) {
        this.sellingPricePerUnit = sellingPricePerUnit;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDiscountTitle() {
        return discountTitle == null ? "" : discountTitle;
    }

    public void setDiscountTitle(String discountTitle) {
        this.discountTitle = discountTitle;
    }

    public float getUnit() {
        return unit;
    }

    public void setUnit(float unit) {
        this.unit = unit;
    }

    public String getUnitTitle() {
        return unitTitle == null ? "" : unitTitle;
    }

    public void setUnitTitle(String unitTitle) {
        this.unitTitle = unitTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMaximumOrderQuantity() {
        return maximumOrderQuantity;
    }

    public void setMaximumOrderQuantity(int maximumOrderQuantity) {
        this.maximumOrderQuantity = maximumOrderQuantity;
    }

    public int getSortPriority() {
        return sortPriority;
    }

    public void setSortPriority(int sortPriority) {
        this.sortPriority = sortPriority;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
