package me.tapumandal.jewellery.domain.promotions;

import me.tapumandal.jewellery.entity.UserPromo;
import me.tapumandal.jewellery.service.Service;

public interface PromotionService extends Service<PromotionDto, Promotion> {
    public UserPromo applyPromotion(String code);
//    public int getAllEntityCount(Pageable pageable, ListFilter listFilter);
}
