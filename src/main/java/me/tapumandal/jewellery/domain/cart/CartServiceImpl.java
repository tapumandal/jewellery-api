package me.tapumandal.jewellery.domain.cart;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import me.tapumandal.jewellery.domain.business_settings.BusinessSettings;
import me.tapumandal.jewellery.domain.business_settings.BusinessSettingsRepository;
import me.tapumandal.jewellery.domain.product.ProductRepository;
import me.tapumandal.jewellery.domain.promotions.Promotion;
import me.tapumandal.jewellery.domain.promotions.PromotionRepository;
import me.tapumandal.jewellery.domain.ref_code.RefCodeRewardRepository;
import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.domain.ref_code.RefCodeReward;
import me.tapumandal.jewellery.entity.Notification;
import me.tapumandal.jewellery.entity.User;
import me.tapumandal.jewellery.repository.UserRepository;
import me.tapumandal.jewellery.util.ApplicationPreferences;
import me.tapumandal.jewellery.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.prefs.Preferences;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    RefCodeRewardRepository refCodeRewardRepository;

    @Autowired
    BusinessSettingsRepository businessSettingsRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private SimpMessagingTemplate template;

    private Notification notifications = new Notification(1);


    private static Preferences preferences;

    private Cart cart;

    public CartServiceImpl(){}

    public CartServiceImpl(Cart cart){
        this.cart = cart;
    }

    @Override
    public Cart create(CartDto cartDto) {

        Cart pro = new Cart(cartDto);

        //System.out.println("SERVICE");
        //System.out.println(new Gson().toJson(pro));

        pro.setInvoiceNo(generateInvoice(pro));

        Optional<Cart> cart;
        try{
            int cartId = cartRepository.create(pro);
            cart = Optional.ofNullable(cartRepository.getCartFirstTime(cartId));

            if(cart.get().getSelectedDiscountName().equals("RefCode") || cart.get().getSelectedDiscountName().equals("PromoCode") || cart.get().getSelectedDiscountName().equals("RefCodeReward")){
                //System.out.println("IT'S "+cart.get().getSelectedDiscountName());
                if(cart.get().getSelectedDiscountName().equals("RefCodeReward")){
                    useRefCodeReward();
                }else{
                    useAppliedPromoCode();
                }
            }else{
                //System.out.println("MY CART: NO A PROMO/REWARD");
            }

            int userId = ApplicationPreferences.getUser().getId();
//            User user = userRepository.getById(userId);

            //System.out.println("USER: "+new Gson().toJson(user));

//            user.getRefCodeReward().setFirstOrder(true);
//            if(user.getUserPromo() != null) {
//                if (user.getUserPromo().getType().equals("RefCode")) {
//                    user.getUserPromo().setActive(false);
//                }
//            }
//            userRepository.update(user);

        }catch (Exception e){
            return null;
        }

        template.convertAndSend("/topic/notification", notifications);

        if(cart.isPresent()){
            return cart.get();
        }else{
            return null;
        }
    }

    private boolean useAppliedPromoCode() {

//        int userId = ApplicationPreferences.getUser().getId();
//        User user = userRepository.getById(userId);
//        if(user.getUserPromo() == null){
//            return false;
//        }
//
//        if(user.getUserPromo().getType().equals("RefCode")) {
//            user.getUserPromo().setActive(false);
//            user.getRefCodeReward().setFirstOrder(true);
//            RefCodeReward refCodeReward = refCodeRewardRepository.getRefCodeByCode(user.getUserPromo().getCode());
//            //System.out.println("ReferredCodeReward B:"+ new Gson().toJson(refCodeReward));
//
//            BusinessSettings businessSettings = businessSettingsRepository.getById(0);
//
//            refCodeReward.setTotalCredit(refCodeReward.getTotalCredit()+businessSettings.getRefCodeReturnReward());
//            refCodeReward.setNumberOfReward(refCodeReward.getNumberOfReward()+1);
//            refCodeReward.setRewardAmountList((refCodeReward.getRewardAmountList()+","+businessSettings.getRefCodeReturnReward()).trim());
//
//            refCodeRewardRepository.update(refCodeReward);
//            //System.out.println("ReferredCodeReward A:"+ new Gson().toJson(refCodeReward));
//            return true;
//
//        }else if(user.getUserPromo().getType().equals("PromoCode")) {
//            Promotion promotion = promotionRepository.applyPromotion(user.getUserPromo().getCode());
//            promotion.setNumberOfApply(promotion.getNumberOfApply()+1);
//            promotionRepository.update(promotion);
//
//            user.getUserPromo().setActive(false);
//            userRepository.update(user);
//            return true;
//        }
        return false;
    }

    private boolean useRefCodeReward() {

//        int userId = ApplicationPreferences.getUser().getId();
//        User user = userRepository.getById(userId);
//
//        RefCodeReward refCodeReward = user.getRefCodeReward();
//
//        BusinessSettings businessSettings = businessSettingsRepository.getById(0);
//
//        int amountCutt = (refCodeReward.getTotalCredit()-refCodeReward.getTotalAmountClaimed()) - businessSettings.getRefCodeCreditAmount();
//        if( amountCutt < 0 ){
//            amountCutt = (refCodeReward.getTotalCredit()-refCodeReward.getTotalAmountClaimed());
//        }else{
//            amountCutt = businessSettings.getRefCodeCreditAmount();
//        }
//        refCodeReward.setNumberOfRewardClaim(refCodeReward.getNumberOfRewardClaim()+1);
//        refCodeReward.setTotalAmountClaimed(refCodeReward.getTotalAmountClaimed()+amountCutt);
//
//        refCodeRewardRepository.update(refCodeReward);
        return true;
    }

    private String generateInvoice(Cart pro) {

        String invoiceCode = "";

        preferences = Preferences.userRoot().node(ApplicationPreferences.class.getName());
        int userID = Integer.parseInt(preferences.get("userId", "0"));
        int totalOrder = cartRepository.getAllByIDEntityCount(null, userID);
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM");
        String[] dateMonth = localDateTime.format(formatter).split("-");

        int day = Integer.valueOf(dateMonth[0]);
        int month = Integer.valueOf(dateMonth[1]);

        String[] areaArray = pro.getArea().split(" ");
        String areaCode = "";

        if(areaArray.length>1){
            areaCode = areaArray[0].substring(0,1)+areaArray[1].substring(0,1);
        }else{
            areaCode = areaArray[0].substring(0,2);
        }

        totalOrder = userID*(totalOrder+1);

        String endCode = "";
        if(totalOrder<10){
            endCode = "000"+String.valueOf(totalOrder);
        }else if(totalOrder<100){
            endCode = "00"+String.valueOf(totalOrder);
        }else if(totalOrder<1000){
            endCode = "0"+String.valueOf(totalOrder);
        }else if(totalOrder<10000){
            endCode = String.valueOf(totalOrder);
        }else{
            endCode = "S"+String.valueOf(totalOrder);
        }

        invoiceCode = areaCode.toUpperCase()+day+month+endCode;
        //System.out.println("InvoiceCode: "+invoiceCode);

        return invoiceCode;
    }

    @Override
    public Cart update(CartDto cartDto) {

        //System.out.println("SERVICE");
        //System.out.println("CART DTO:"+new Gson().toJson(cartDto));
        Optional<Cart> cart;
//        try{
            Cart tmpCart = cartRepository.getById(cartDto.getId());
            //System.out.println("CART TMP:"+new Gson().toJson(tmpCart));
            tmpCart.setStatus(cartDto.getStatus());
            int proId = cartRepository.update(tmpCart);
            cart = Optional.ofNullable(cartRepository.getById(proId));
            //System.out.println("CART GET:"+new Gson().toJson(cart.get()));
//        }catch (Exception e){
//            return null;
//        }

        if(cart.isPresent()){
            return cart.get();
        }else{
            return null;
        }

    }

    @Override
    public List<Cart> getAll(Pageable pageable, ListFilter listFilter) {

        Optional<List<Cart>> carts = Optional.ofNullable(cartRepository.getAll(pageable, listFilter));
        //System.out.println("SERVICE ADMIN CART LIST"+new Gson().toJson(carts.get()));

        List<Cart> cartList = carts.get();

        int i = 0;
        for (Cart cartTmp: carts.get()) {
            int j = 0;
            for (CartProduct cartProduct: cartTmp.getProductList()) {
                //System.out.println("CART PRODUCTS: "+new Gson().toJson(cartProduct));

                cartList.get(i).getProductList().get(j).setBuyingPricePerUnitAssigned(productRepository.getById(cartProduct.getProductId()).getBuyingPricePerUnit());

                j++;
            }
            i++;
        }

        if(carts.isPresent()){
            return carts.get();
        }else{
            return null;
        }
    }

    @Override
    public Cart getById(int id) {

        Optional<Cart> cart = Optional.ofNullable(cartRepository.getById(id));

        if(cart.isPresent()){
            return cart.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
//        try {
            return cartRepository.delete(id);
//        }catch (Exception ex){
//            return false;
//        }
    }

    @Override
    public Cart getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Cart> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        Optional<Cart> cart = Optional.ofNullable(cartRepository.getById(id));
        if(cart.isPresent()){
            if(cart.get().isActive()){
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean isDeleted(int id) {
        return false;
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return cartRepository.getPageable(pageable);
    }

    @Override
    public int getAllEntityCount(Pageable pageable, ListFilter listFilter) {
        return cartRepository.getAllEntityCount(pageable, listFilter);
    }

    @Override
    public List<Cart> getAllByUserID(Pageable pageable) {

        preferences = Preferences.userRoot().node(ApplicationPreferences.class.getName());
        int userID = Integer.parseInt(preferences.get("userId", "0"));

        Optional<List<Cart>> carts = Optional.ofNullable(cartRepository.getAllByUserID(userID, pageable));

        if(carts.isPresent()){
            return carts.get();
        }else{
            return null;
        }
    }

    @Override
    public int getAllByIDEntityCount(Pageable pageable, int id) {
        return cartRepository.getAllByIDEntityCount(pageable, id);
    }
}
