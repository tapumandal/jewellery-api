package me.tapumandal.jewellery.domain.business_settings;

import com.sun.istack.Nullable;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "business_settings")
@Component
public class BusinessSettings{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "update_menu")
    protected boolean updateMenu = true;

    @Column(name = "delivery_charge")
    protected int deliveryCharge = 0;

    @Column(name = "default_discount_btn")
    protected String defaultDiscountBtn = "radioSpecialOffer"; // radioOnProduct/radioSpecialOffer

    @Column(name = "discount_name")
    protected String discountName = "Special Offer"; // Special Offer(Eid/Puja/NewYear)

    @Column(name = "discount_type")
    protected String discountType = "TotalPercentage"; // TotalPercentage/OverallAmount/ProductDiscount


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "business_settings_id_discount_type_condition", referencedColumnName = "id")
    protected List<DiscountTypeCondition> discountTypeCondition;

    @Column(name = "discountBannerMessage", length = 500)
    protected String discountBannerMessage;

    @Column(name = "discount_banner", length = 500)
    protected String discountBanner;

    @Column(name = "payment_discount_message", length = 500)
    protected String paymentDiscountMessage = "If there is any payment discount";

    @Column(name = "payment_discount_banner", length = 500)
    protected String paymentDiscountBanner;

    @Column(name = "cardPayment_discount_name")
    protected String cardPaymentDiscountName = "Debit/Credit Card";

    @Column(name = "card_payment_type")
    protected String cardPaymentDiscountType = "TotalPercentage"; // TotalPercentage/OverallAmount

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "business_settings_id_card_payment_condition", referencedColumnName = "id")
    protected List<DiscountTypeCondition> cardPaymentCondition;

    @Column(name = "mobile_payment_discount_name")
    protected String mobilePaymentDiscountName = "BKash/Rocket/Nagad";

    @Column(name = "mobile_payment_type")
    protected String mobilePaymentDiscountType = "TotalPercentage"; // TotalPercentage/OverallAmount

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "business_settings_id_mobile_payment_condition", referencedColumnName = "id")
    protected List<DiscountTypeCondition> mobilePaymentCondition;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    @JoinColumn(name = "version_control_id", referencedColumnName = "id")
//    protected VersionControl versionControl;

    @OneToOne(targetEntity = VersionControl.class, cascade = CascadeType.ALL)
    protected VersionControl versionControl;



    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Nullable
    @Column(name = "ref_code_return_reward")
    protected int refCodeReturnReward = 0;

    @Nullable
    @Column(name = "ref_code_apply_reward")
    protected int refCodeApplyReward = 0;

    @Nullable
    @Column(name = "ref_code_minimum_purchase")
    protected int refCodeMinimumPurchase = 0;

    @Nullable
    @Column(name = "ref_code_credit_amount")
    protected int refCodeCreditAmount = 0;

    public BusinessSettings(){}

    public BusinessSettings(BusinessSettingsDto businessSettingsDto) {
        this.id = businessSettingsDto.getId();
        this.updateMenu = businessSettingsDto.isUpdateMenu();
        this.deliveryCharge = businessSettingsDto.getDeliveryCharge();
        this.defaultDiscountBtn = businessSettingsDto.getDefaultDiscountBtn();
        this.discountName = businessSettingsDto.getDiscountName();
        this.discountType = businessSettingsDto.getDiscountType();

        this.discountTypeCondition = new ArrayList<>();
        for (DiscountTypeConditionDto dTypeConditionDto: businessSettingsDto.getDiscountTypeCondition()){
            DiscountTypeCondition dTypeCondition = new DiscountTypeCondition(dTypeConditionDto);
            this.discountTypeCondition.add(dTypeCondition);
        }

        this.discountBannerMessage = businessSettingsDto.getDiscountBannerMessage();
        this.discountBanner = businessSettingsDto.getDiscountBanner();
        this.paymentDiscountMessage = businessSettingsDto.getPaymentDiscountMessage();
        this.paymentDiscountBanner = businessSettingsDto.getPaymentDiscountBanner();
        this.cardPaymentDiscountName = businessSettingsDto.getCardPaymentDiscountName();
        this.cardPaymentDiscountType = businessSettingsDto.getCardPaymentDiscountType();

        this.cardPaymentCondition = new ArrayList<>();
        for (DiscountTypeConditionDto dTypeConditionDto: businessSettingsDto.getCardPaymentCondition()){
            DiscountTypeCondition dTypeCondition = new DiscountTypeCondition(dTypeConditionDto);
            this.cardPaymentCondition.add(dTypeCondition);
        }

        this.mobilePaymentDiscountName = businessSettingsDto.getMobilePaymentDiscountName();
        this.mobilePaymentDiscountType = businessSettingsDto.getMobilePaymentDiscountType();

        this.mobilePaymentCondition = new ArrayList<>();
        for (DiscountTypeConditionDto dTypeConditionDto: businessSettingsDto.getMobilePaymentCondition()){
            DiscountTypeCondition dTypeCondition = new DiscountTypeCondition(dTypeConditionDto);
            this.mobilePaymentCondition.add(dTypeCondition);
        }

        this.versionControl = new VersionControl(businessSettingsDto.getVersionControlDto());

        this.refCodeReturnReward = businessSettingsDto.getRefCodeReturnReward();
        this.refCodeApplyReward = businessSettingsDto.getRefCodeApplyReward();

        this.refCodeMinimumPurchase = businessSettingsDto.getRefCodeMinimumPurchase();

        this.refCodeCreditAmount = businessSettingsDto.getRefCodeCreditAmount();
    }
}
