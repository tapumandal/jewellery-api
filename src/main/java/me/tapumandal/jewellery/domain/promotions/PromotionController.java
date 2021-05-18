package me.tapumandal.jewellery.domain.promotions;

import com.google.gson.Gson;
import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.entity.UserPromo;
import me.tapumandal.jewellery.entity.dto.ListFilterDto;
import me.tapumandal.jewellery.util.CommonResponseArray;
import me.tapumandal.jewellery.util.CommonResponseSingle;
import me.tapumandal.jewellery.util.ControllerHelper;
import me.tapumandal.jewellery.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/promotion")
public class PromotionController extends ControllerHelper<Promotion> {

    @Autowired
    PromotionService promotionService;

    @PostMapping(path = "/create")
    public CommonResponseSingle createPromotion(@ModelAttribute PromotionDto promotionDto, HttpServletRequest request) {


        //System.out.println("CONTROLLER");
        //System.out.println(new Gson().toJson(promotionDto));

        storeUserDetails(request);
        Promotion promotion = promotionService.create(promotionDto);

        if (promotion != null) {
            return response(true, HttpStatus.CREATED, "New promotion inserted successfully", promotion);
        } else if (promotion == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (Promotion) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Promotion) null);
    }

    @GetMapping(path = "/get")
    public CommonResponseArray getPromotion(HttpServletRequest request) {

        storeUserDetails(request);

//        List<MenuList> menuLists = promotionService.getPromotion();
//
//
//        if (menuLists != null) {
//            return responseCustom(true, HttpStatus.CREATED, "New promotion inserted successfully", menuLists);
//        } else if (menuLists == null) {
//            return responseCustom(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (List<MenuList>) null);
//        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (ArrayList<Promotion>) null);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<CommonResponseArray<Promotion>> getPromotionList(@ModelAttribute ListFilterDto listFilterDto, HttpServletRequest request, Pageable pageable) {

        System.out.println(new Gson().toJson(listFilterDto));

        ListFilter listFilter = new ListFilter(listFilterDto);

        storeUserDetails(request);
        List<Promotion> promotions = promotionService.getAll(pageable, listFilter);

        MyPagenation myPagenation = managePagenation(request, promotionService.getAllEntityCount(pageable, listFilter), pageable);

        if (!promotions.isEmpty()) {
            return ResponseEntity.ok(response(true, HttpStatus.FOUND, "All promotion list", promotions, myPagenation));
        } else if (promotions.isEmpty()) {
            return ResponseEntity.ok(response(false, HttpStatus.NO_CONTENT, "No promotion found", new ArrayList<Promotion>(), myPagenation));
        } else {
            return ResponseEntity.ok(response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Promotion>(), myPagenation));
        }
    }

    @PostMapping(path = "/update")
    public CommonResponseSingle updatePromotion(@ModelAttribute PromotionDto promotionDto, HttpServletRequest request) {

        System.out.println("PROMOTION UPDATE:");
        System.out.println(new Gson().toJson(promotionDto));
        storeUserDetails(request);

        Promotion promotion = promotionService.update(promotionDto);

        if (promotion != null) {
            return response(true, HttpStatus.OK, "New promotion inserted successfully", promotion);
        } else if (promotion == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Promotion) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Promotion) null);
    }


    @PostMapping(path = "/consumer/apply/{code}")
    public CommonResponseSingle applyPromotion(@PathVariable("code") String code, HttpServletRequest request) {

        storeUserDetails(request);
        System.out.println("CODE:"+code);
        UserPromo userPromo = promotionService.applyPromotion(code);
        System.out.println("CONROLLER:"+new Gson().toJson(userPromo));
        if (userPromo != null) {
            return new CommonResponseSingle(true, HttpStatus.OK, "New promotion inserted successfully", userPromo);
        } else if (userPromo == null) {
            return new CommonResponseSingle(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Promotion) null);
        }

        return new CommonResponseSingle(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Promotion) null);
    }


}