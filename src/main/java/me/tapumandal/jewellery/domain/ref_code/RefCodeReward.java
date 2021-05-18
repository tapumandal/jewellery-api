package me.tapumandal.jewellery.domain.ref_code;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "ref_code_reward")
public class RefCodeReward {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "code")
    protected String code = "";

    @Column(name = "total_credit")
    protected int totalCredit;

    @Column(name = "number_of_reward")
    protected int numberOfReward = 0;

    @Column(name = "number_of_reward_claim")
    protected int numberOfRewardClaim = 0;

    @Column(name = "total_amount_claimed")
    protected int totalAmountClaimed = 0;

    @Column(name = "reward_amount_list", length = 300)
    protected String rewardAmountList = ""; //Separated by comma

    @Column(name = "first_order", columnDefinition = "boolean default false")
    protected boolean firstOrder = false;

    @Column(name = "is_active", columnDefinition = "boolean default 1")
    protected boolean isActive = true;

    @Column(name = "ref_code_reward_title", length = 300)
    protected String refCodeRewardTitle = "";

    @Column(name = "ref_code_reward_message", length = 500)
    protected String refCodeRewardMessage = "";

    @Column(name = "is_deleted", columnDefinition = "boolean default 0")
    private boolean isDeleted = false;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public RefCodeReward() {}

    public RefCodeReward(RefCodeRewardDto refCodeRewardDto) {
        this.id = refCodeRewardDto.getId();
        this.code = refCodeRewardDto.getCode();
        this.totalCredit = refCodeRewardDto.getTotalCredit();
        this.numberOfReward = refCodeRewardDto.getNumberOfReward();
        this.numberOfRewardClaim = refCodeRewardDto.getNumberOfRewardClaim();
        this.totalAmountClaimed = refCodeRewardDto.getTotalAmountClaimed();
        this.rewardAmountList = refCodeRewardDto.getRewardAmountList();
        this.firstOrder = refCodeRewardDto.isFirstOrder();
        this.isActive = refCodeRewardDto.isActive();
        this.refCodeRewardTitle = refCodeRewardDto.getRefCodeRewardTitle();
        this.refCodeRewardMessage = refCodeRewardDto.getRefCodeRewardMessage();
    }
}