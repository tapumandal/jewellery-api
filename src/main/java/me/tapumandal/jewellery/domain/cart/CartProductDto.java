package me.tapumandal.jewellery.domain.cart;

import com.sun.istack.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Component
public class CartProductDto {

    private int id;

    private int productId;

    private String name;

    private String image;

    private String company;

    private String categories;

    private String[] preSelectedCategories;

    private String description;

    private int sellingPricePerUnit;

    private int discountPrice;

    private String discountTitle;

    private int unit;

    private String unitTitle;

    private int quantity;

    private int deliveryCharge = 0;

    private int maximumOrderQuantity = 20;

    private int orderQuantity = 0;


    private boolean is_active = true;

    private boolean is_deleted = false;

    private Date created_at;

    private Date updated_at;


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

    public String getImage() {
        return image == null ? "" : image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
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

    public int getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(int deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public int getMaximumOrderQuantity() {
        return maximumOrderQuantity;
    }

    public void setMaximumOrderQuantity(int maximumOrderQuantity) {
        this.maximumOrderQuantity = maximumOrderQuantity;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}

