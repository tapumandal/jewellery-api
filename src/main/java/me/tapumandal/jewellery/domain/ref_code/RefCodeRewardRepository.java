package me.tapumandal.jewellery.domain.ref_code;

import me.tapumandal.jewellery.repository.Repository;

public interface RefCodeRewardRepository extends Repository<RefCodeReward> {

    public RefCodeReward getRefCodeRewardFirstTime(int refCodeRewardId);
    public RefCodeReward getRefCodeByCode(String code);
}
