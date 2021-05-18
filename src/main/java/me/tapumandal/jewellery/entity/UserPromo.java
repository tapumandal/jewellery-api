package me.tapumandal.jewellery.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import me.tapumandal.jewellery.domain.promotions.Promotion;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user_promo")
public class UserPromo {

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
    protected String area = ""; //All(Could be Everywhere) or Specific

    @Column(name = "is_active", columnDefinition = "boolean default 1")
    protected boolean active = true;

    @Column(name = "promo_title", length = 300)
    protected String promoTitle = "";

    @Column(name = "promo_message", length = 500)
    protected String promoMessage = "";

    @Column(name = "code_list", length = 500)
    protected String codeList = ""; //Separated by Comma

    @Column(name = "credit_list", length = 500)
    protected String creditList = ""; //Separated by Comma

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    protected Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    protected Timestamp updatedAt;

    public UserPromo() {}

    public UserPromo(Promotion promotion) {
        this.code = promotion.getCode();
        this.credit = promotion.getCredit();
        this.type = promotion.getType();
        this.startDate = promotion.getStartDate();
        this.expireDate = promotion.getExpireDate();
        this.minimumPurchase = promotion.getMinimumPurchase();
        this.area = promotion.getArea();
        this.active = promotion.isActive();
        this.codeList = promotion.getCode();
        this.creditList = promotion.getCredit()+"";
        this.promoTitle = promotion.getPromoTitle();
        this.promoMessage = promotion.getPromoMessage();
    }

}