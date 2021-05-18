package me.tapumandal.jewellery.domain.business_settings;

import com.google.gson.annotations.SerializedName;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by TapuMandal on 11/25/2020.
 * For any query ask online.tapu@gmail.com
 */
@Entity
@Table(name = "discount_type_condition")
@Component
public class DiscountTypeCondition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected int id;

    @SerializedName("minimum_purchase_limit")
    private int minimumPurchaseLimit = 0;

    @SerializedName("discounted_amount")
    private int discountedAmount = 0;

    @SerializedName("maximum_discounted_amount")
    private int maximumDiscountedAmount = 0;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public DiscountTypeCondition() {
    }

    public DiscountTypeCondition(DiscountTypeConditionDto discountTypeConditionDto) {
        this.minimumPurchaseLimit = discountTypeConditionDto.getMinimumPurchaseLimit();
        this.discountedAmount = discountTypeConditionDto.getDiscountedAmount();
        this.maximumDiscountedAmount = discountTypeConditionDto.getMaximumDiscountedAmount();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinimumPurchaseLimit() {
        return minimumPurchaseLimit;
    }

    public void setMinimumPurchaseLimit(int minimumPurchaseLimit) {
        this.minimumPurchaseLimit = minimumPurchaseLimit;
    }

    public int getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(int discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    public int getMaximumDiscountedAmount() {
        return maximumDiscountedAmount;
    }

    public void setMaximumDiscountedAmount(int maximumDiscountedAmount) {
        this.maximumDiscountedAmount = maximumDiscountedAmount;
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
}
