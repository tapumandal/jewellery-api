package me.tapumandal.jewellery.domain.ref_code;

import lombok.Data;
import me.tapumandal.jewellery.entity.User;
import org.springframework.stereotype.Component;

@Component
@Data
public class RefCodeRewardDto {

    protected int id;

    protected String code = "";

    protected int totalCredit;

    protected int numberOfReward = 0;

    protected int numberOfRewardClaim = 0;

    protected int totalAmountClaimed = 0;

    protected String rewardAmountList = ""; //Separated by comma

    private boolean firstOrder = false;

    private boolean isActive = true;

    protected String refCodeRewardTitle = "";

    protected String refCodeRewardMessage = "";

    private boolean isDeleted = false;

    private User user;
}
