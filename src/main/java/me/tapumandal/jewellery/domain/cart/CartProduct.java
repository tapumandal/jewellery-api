package me.tapumandal.jewellery.domain.cart;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by TapuMandal on 11/7/2020.
 * For any query ask online.tapu@gmail.com
 */

@Entity
@Table(name = "cart_product")
public class CartProduct{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "company")
    private String company;

    @Column(name = "categories")
    private String categories;

    @Column(name = "pre_selected_categories")
    private String[] preSelectedCategories;

    @Column(name = "description")
    private String description;

    private float buyingPricePerUnitAssigned = 0;

    @Column(name = "selling_price_per_unit")
    private int sellingPricePerUnit;

    @Column(name = "discount_price")
    private int discountPrice;

    @Column(name = "discount_title")
    private String discountTitle;

    @Column(name = "unit")
    private int unit;

    @Column(name = "unit_title")
    private String unitTitle;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "delivery_charge")
    private int deliveryCharge;

    @Column(name = "maximum_order_quantity")
    private int maximumOrderQuantity;

    @Column(name = "order_quantity")
    private int orderQuantity;

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

//    @JoinColumn(name = "cart_id", referencedColumnName = "id")
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "cart_id")
//    private Cart cart;

    public CartProduct() {
    }

    public CartProduct(CartProductDto cartProductDto) {
        this.productId = cartProductDto.getId();
        this.name = cartProductDto.getName();
        this.image = cartProductDto.getImage();
        this.company = cartProductDto.getCompany();
        this.categories = cartProductDto.getCategories();
        this.preSelectedCategories = cartProductDto.getPreSelectedCategories();
        this.description = cartProductDto.getDescription();
        this.sellingPricePerUnit = cartProductDto.getSellingPricePerUnit();
        this.discountPrice = cartProductDto.getDiscountPrice();
        this.discountTitle = cartProductDto.getDiscountTitle();
        this.unit = cartProductDto.getUnit();
        this.unitTitle = cartProductDto.getUnitTitle();
        this.quantity = cartProductDto.getQuantity();
        this.deliveryCharge = cartProductDto.getDeliveryCharge();
        this.maximumOrderQuantity = cartProductDto.getMaximumOrderQuantity();
        this.orderQuantity = cartProductDto.getOrderQuantity();
        this.isActive = cartProductDto.isIs_active();
        this.isDeleted = cartProductDto.isIs_deleted();
        this.createdAt = cartProductDto.getCreated_at();
        this.updatedAt = cartProductDto.getUpdated_at();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public float getBuyingPricePerUnitAssigned() {
        return buyingPricePerUnitAssigned;
    }

    public void setBuyingPricePerUnitAssigned(float buyingPricePerUnitAssigned) {
        this.buyingPricePerUnitAssigned = buyingPricePerUnitAssigned;
    }
}