package me.tapumandal.jewellery.domain.promotions;

import lombok.SneakyThrows;
import me.tapumandal.jewellery.domain.business_settings.BusinessSettings;
import me.tapumandal.jewellery.domain.business_settings.BusinessSettingsRepository;
import me.tapumandal.jewellery.domain.ref_code.RefCodeRewardRepository;
import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.entity.User;
import me.tapumandal.jewellery.entity.UserPromo;
import me.tapumandal.jewellery.repository.UserRepository;
import me.tapumandal.jewellery.util.ApplicationPreferences;
import me.tapumandal.jewellery.util.MyPagenation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import me.tapumandal.jewellery.domain.ref_code.RefCodeReward;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplicationPreferences applicationPreferences;

    @Autowired
    BusinessSettingsRepository businessSettingsRepository;

    @Autowired
    RefCodeRewardRepository refCodeRewardRepository;

    private Promotion promotion;

    public PromotionServiceImpl(){}

    public PromotionServiceImpl(Promotion promotion){
        this.promotion = promotion;
    }

    @Override
    public Promotion create(PromotionDto promotionDto) {

        Promotion entity = new Promotion(promotionDto);
        //System.out.println("SERVICE: ");
        //System.out.println(new Gson().toJson(entity));
//        try{
        int promotionID = promotionRepository.create(entity);
//        }catch (Exception e){
//            return null;
//        }
        //System.out.println("RETURNED: ");
        //System.out.println(new Gson().toJson(promotionRepository.getById(promotionID)));
        return promotionRepository.getById(promotionID);
    }

    @Override
    public Promotion update(PromotionDto promotionDto) {


        Promotion entity = new Promotion(promotionDto);
        //System.out.println("SERVICE UPDATE: ");
        //System.out.println(new Gson().toJson(entity));
        Promotion existingPromotion = promotionRepository.getById(entity.getId());
        BeanUtils.copyProperties(entity, existingPromotion);
//        try{
            int promotionID = promotionRepository.update(existingPromotion);
//        }catch (Exception e){
//            return null;
//        }
        return promotionRepository.getById(promotionID);
    }

    @Override
    public UserPromo applyPromotion(String code) {

//        int userId = ApplicationPreferences.getUser().getId();
//        User user = userRepository.getById(userId);
//
////        Check ReferredCode First
//        RefCodeReward refCodeReward = null;
//        if(!user.getRefCodeReward().isFirstOrder()) {
//            refCodeReward = refCodeRewardRepository.getRefCodeByCode(code);
//        }
//        //System.out.println("S RefCodeReward:"+new Gson().toJson(refCodeReward));
//
//        if(refCodeReward == null){
//            Promotion promotion = promotionRepository.applyPromotion(code);
//            //System.out.println("S promoCode:"+new Gson().toJson(promotion));
//
//            if(promotion != null){
//                //System.out.println("USER : "+new Gson().toJson(user));
//                UserPromo userPromo = new UserPromo();
//                if (user.getUserPromo() == null) {
//                    //System.out.println("IF: ");
//                    userPromo = new UserPromo(promotion);
//                }else if(!user.getUserPromo().getCodeList().contains(code)){
//                    //System.out.println("ELSE IF: ");
//                    userPromo = updateUserPromo(user.getUserPromo(), promotion);
//                }else{
//                    return null;
//                }
//
//                //System.out.println("userPromo : "+new Gson().toJson(userPromo));
//                user.setUserPromo(userPromo);
////                userRepository.update(user);
//                return userRepository.getById(user.getId()).getUserPromo();
//            }else{
//                return null;
//            }
//        }else if(refCodeReward != null && user.getUserPromo() == null){
////            user.getUserPromo() == null this check means the orderFirst == false
//            //System.out.println("ELSE IF");
//            UserPromo userPromo = new UserPromo();
//            userPromo = updateUserPromoWithRefCodeReward(userPromo, refCodeReward);
//            user.setUserPromo(userPromo);
//            userRepository.update(user);
//
//            return userRepository.getById(user.getId()).getUserPromo();
////        If Yes Check First Order or Not //
////        Update UserPromo Data //
////        IF First make first order false //When Order
////        Give Reward Who referred // When Order
//        }else{
//            //System.out.println("ELSE");
//            return null;
//        }
////        Promotion promotion =  promotionRepository.applyPromotion(code);
////        List<Promotion> promotionLists = new Gson().fromJson(promotion.getPromotion(), List.class);
////        return null;
        return null;
    }

    @SneakyThrows
    private UserPromo updateUserPromoWithRefCodeReward(UserPromo userPromo, RefCodeReward refCodeReward){

        BusinessSettings businessSettings = businessSettingsRepository.getById(0);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        userPromo.setCode(refCodeReward.getCode());
        userPromo.setCredit(businessSettings.getRefCodeApplyReward());
        userPromo.setType("RefCode");

        userPromo.setStartDate(Date.valueOf("2000-12-01"));
        userPromo.setExpireDate(Date.valueOf("2050-12-01"));

        userPromo.setMinimumPurchase(businessSettings.getRefCodeMinimumPurchase());
        userPromo.setArea("Everywhere");
        userPromo.setCodeList(userPromo.getCodeList()+","+refCodeReward.getCode().trim());
        userPromo.setCreditList((userPromo.getCreditList()+","+businessSettings.getRefCodeApplyReward()).trim());
        userPromo.setPromoTitle("You have applied "+refCodeReward.getCode()+" and will get "+businessSettings.getRefCodeApplyReward()+" taka off on your first purchase");
        userPromo.setPromoMessage("This credit will be applicable only for the first order. " +
                "You must purchase at least "+businessSettings.getRefCodeMinimumPurchase()+" taka. "+
                "The person, who's code you have applied will get another "+businessSettings.getRefCodeReturnReward()+" taka!");

//        userPromo.setPromoMessage("This code will be only applicable for the first order. " +
//                "You must purchase at least "+businessSettings.getRefCodeMinimumPurchase()+" taka. " +
//                "The person, who's code your are using will get another "+businessSettings.getRefCodeReturnReward()+" taka off!");

        return userPromo;
    }

    private UserPromo updateUserPromo(UserPromo userPromo, Promotion promotion){

        userPromo.setCode(promotion.getCode());
        userPromo.setCredit(promotion.getCredit());
        userPromo.setType(promotion.getType());
        userPromo.setStartDate(promotion.getStartDate());
        userPromo.setExpireDate(promotion.getExpireDate());
        userPromo.setMinimumPurchase(promotion.getMinimumPurchase());
        userPromo.setArea(promotion.getArea());
        userPromo.setActive(promotion.isActive());
        userPromo.setCodeList(userPromo.getCodeList()+","+promotion.getCode().trim());
        userPromo.setCreditList((userPromo.getCreditList()+","+promotion.getCredit()).trim());
        userPromo.setPromoTitle(promotion.getPromoTitle());
        userPromo.setPromoMessage(promotion.getPromoMessage());

        return userPromo;
    }

    @Override
    public int getAllEntityCount(Pageable pageable, ListFilter listFilter) {
        return promotionRepository.getAllEntityCount(pageable, listFilter);
    }

    @Override
    public List<Promotion> getAll(Pageable pageable, ListFilter listFilter) {
        Optional<List<Promotion>> promotions = Optional.ofNullable(promotionRepository.getAll(pageable, listFilter));

        if(promotions.isPresent()){
            return promotions.get();
        }else{
            return null;
        }
    }

    @Override
    public Promotion getById(int id) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public Promotion getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Promotion> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return false;
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return null;
    }

}
