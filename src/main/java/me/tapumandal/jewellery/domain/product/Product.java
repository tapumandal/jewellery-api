package me.tapumandal.jewellery.domain.product;

import me.tapumandal.jewellery.domain.image.Image;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "product")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "imageName")
    private String imageName;

    @Column(name = "company")
    private String company;

    @Column(name = "categories", length = 300)
    private String categories;

    @Column(name = "preSelectedCategories", length = 300)
    private String[] preSelectedCategories;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "buyingPricePerUnit")
    private float buyingPricePerUnit;

    @Column(name = "sellingPricePerUnit")
    private int sellingPricePerUnit;

    @Column(name = "discountPrice")
    private int discountPrice;

    @Column(name = "discountTitle")
    private String discountTitle;

    @Column(name = "unit")
    private float unit;

    @Column(name = "unitTitle")
    private String unitTitle;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "maximumOrderQuantity")
    private int maximumOrderQuantity = 20;

    @Column(name = "sortPriority")
    private int sortPriority = 0;

    @Column(name = "is_active", columnDefinition = "boolean default 1")
    private boolean isActive = true;

    @Column(name = "is_deleted", columnDefinition = "boolean default 0")
    private boolean isDeleted = false;

    @Column(name = "created_at", updatable=false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private List<Image> productImages = new ArrayList<Image>();

    public Product() {
    }

    public Product(ProductDto productDto) {

        this.setId(productDto.getId());
        this.setName(productDto.getName());
//        this.setImage(productDto.getImage());
        this.setCompany(productDto.getCompany());
        this.setCategories(productDto.getCategories());
        this.setPreSelectedCategories(productDto.getPreSelectedCategories());
        this.setDescription(productDto.getDescription());
        this.setBuyingPricePerUnit(productDto.getBuyingPricePerUnit());
        this.setSellingPricePerUnit(productDto.getSellingPricePerUnit());
        this.setDiscountPrice(productDto.getDiscountPrice());
        this.setDiscountTitle(productDto.getDiscountTitle());
        this.setUnit(productDto.getUnit());
        this.setUnitTitle(productDto.getUnitTitle());
        this.setQuantity(productDto.getQuantity());
        this.setMaximumOrderQuantity(productDto.getMaximumOrderQuantity());
        this.setActive(productDto.isActive());
        this.setDeleted(productDto.isDelete());
    }

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

    public String getImageName() {
        return imageName == null ? "" : imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Image> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<Image> productImages) {
        this.productImages = productImages;
    }
}
