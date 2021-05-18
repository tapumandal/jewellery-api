package me.tapumandal.jewellery.domain.business_settings;


import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Data
@Component
public class BusinessSettingsDto implements Serializable {

    @SerializedName("id")
    protected int id;

    @SerializedName("updateMenu")
    protected boolean updateMenu = true;

    @SerializedName("deliveryCharge")
    protected int deliveryCharge = 20;

    @SerializedName("defaultDiscountBtn")
    protected String defaultDiscountBtn = "radioSpecialOffer"; // radioOnProduct/radioSpecialOffer

    @SerializedName("discountName")
    protected String discountName = "Special Offer"; // Special Offer(Eid/Puja/NewYear)

    @SerializedName("discountType")
    protected String discountType = "TotalPercentage"; // TotalPercentage/OverallAmount/ProductDiscount

    @SerializedName("discountTypeCondition")
    protected List<DiscountTypeConditionDto> discountTypeCondition;


    @SerializedName("discountBannerMessage")
    protected String discountBannerMessage;

    @SerializedName("discountBanner")
    protected String discountBanner;

    @SerializedName("paymentDiscountMessage")
    protected String paymentDiscountMessage = "If there is any payment discount";

    @SerializedName("paymentDiscountBanner")
    protected String paymentDiscountBanner;

    @SerializedName("cardPaymentDiscountName")
    protected String cardPaymentDiscountName = "Debit/Credit Card";

    @SerializedName("cardPaymentType")
    protected String cardPaymentDiscountType = "TotalPercentage"; // TotalPercentage/OverallAmount

    @SerializedName("cardPaymentCondition")
    protected List<DiscountTypeConditionDto> cardPaymentCondition;

    @SerializedName("mobilePaymentDiscountName")
    protected String mobilePaymentDiscountName = "BKash/Rocket/Nagad";

    @SerializedName("mobilePaymentType")
    protected String mobilePaymentDiscountType = "TotalPercentage"; // TotalPercentage/OverallAmount

    @SerializedName("mobilePaymentCondition")
    protected List<DiscountTypeConditionDto> mobilePaymentCondition;

    @SerializedName("versionControlDto")
    protected VersionControlDto versionControlDto;

    @SerializedName("refCodeReturnReward")
    protected int refCodeReturnReward = 0;

    @SerializedName("refCodeApplyReward")
    protected int refCodeApplyReward = 0;

    @SerializedName("refCodeMinimumPurchase")
    protected int refCodeMinimumPurchase = 0;

    @SerializedName("refCodeCreditAmount")
    protected int refCodeCreditAmount = 0;
}
