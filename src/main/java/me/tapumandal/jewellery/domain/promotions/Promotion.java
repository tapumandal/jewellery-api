package me.tapumandal.jewellery.domain.promotions;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "code")
    protected String code = "";

    @Column(name = "credit")
    protected int credit = 0;

    @Column(name = "type")
    protected String type = ""; //RefCode/PromoCode

    @Column(name = "start_date")
    protected Date startDate;

    @Column(name = "expire_date")
    protected Date expireDate;

    @Column(name = "minimum_purchase")
    protected int minimumPurchase = 0;

    @Column(name = "area")
    protected String area = ""; //All or Specific

    @Column(name = "is_active", columnDefinition = "boolean default 1")
    protected boolean active = true;

    @Column(name = "number_of_apply")
    protected int numberOfApply = 0;

    @Column(name = "promo_title", length = 300)
    protected String promoTitle = "";

    @Column(name = "promo_message", length = 500)
    protected String promoMessage = "";

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    protected Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    protected Timestamp updatedAt;

    public Promotion() {}

    public Promotion(PromotionDto promotionDto) {
        this.id = promotionDto.getId();
        this.code = promotionDto.getCode();
        this.type = promotionDto.getType();
        this.startDate = Date.valueOf(promotionDto.getStartDate());
        this.expireDate = Date.valueOf(promotionDto.getExpireDate());
        this.credit = promotionDto.getCredit();
        this.minimumPurchase = promotionDto.getMinimumPurchase();
        this.area = promotionDto.getArea();
        this.active = promotionDto.isActive();
        this.promoTitle = promotionDto.getPromoTitle();
        this.promoMessage = promotionDto.getPromoMessage();
    }
}