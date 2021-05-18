package me.tapumandal.jewellery.domain.cart;

import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class CartController extends ControllerHelper<Cart> {

    @Autowired
    CartService cartService;

    private static Preferences preferences;

    @PostMapping(path = "/cart/consumer/create")
    public CommonResponseSingle createCart(@RequestBody CartDto cartDto, HttpServletRequest request) {

        //System.out.println("CONTROLLER");
        //System.out.println(new Gson().toJson(cartDto));

        storeUserDetails(request);

        Cart cart = cartService.create(cartDto);

        if (cart != null) {
            return response(true, HttpStatus.CREATED, "New cart inserted successfully", cart);
        } else if (cart == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (Cart) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Cart) null);
    }

    @GetMapping(path = "/cart/{id}")
    public CommonResponseSingle<Cart> getCart(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        Cart cart = cartService.getById(id);

        if (cart != null) {
            return response(true, HttpStatus.FOUND, "Cart by id: " + id, cart);
        } else if (cart == null) {
            return response(false, HttpStatus.NO_CONTENT, "Cart not found or deleted", (Cart) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (Cart) null);
        }
    }

    @GetMapping(path = "/cart/list")
    public CommonResponseArray<Cart> getAll(@ModelAttribute ListFilter listFilter, HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);
        //System.out.println("CART CONTROLLER:"+new Gson().toJson(listFilter));
        List<Cart> carts = cartService.getAll(pageable, listFilter);

        MyPagenation myPagenation = managePagenation(request, cartService.getAllEntityCount(pageable, listFilter), pageable);
        //System.out.println("CART:"+new Gson().toJson(carts));
        if (!carts.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All cart list", carts, myPagenation);
        } else if (carts.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No cart found", new ArrayList<Cart>(), myPagenation);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Cart>(), myPagenation);
        }

    }

    @GetMapping(path = "/cart/consumer/list")
    public CommonResponseArray<Cart> getAllByUserID(HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);
        preferences = Preferences.userRoot().node(ApplicationPreferences.class.getName());
        int userID = Integer.parseInt(preferences.get("userId", "0"));

        List<Cart> carts = cartService.getAllByUserID(pageable);

        MyPagenation myPagenation = managePagenation(request, cartService.getAllByIDEntityCount(pageable, userID), pageable);
        //System.out.println("CART:"+new Gson().toJson(carts));
        if (!carts.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All cart list", carts, myPagenation);
        } else if (carts.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No cart found", new ArrayList<Cart>(), myPagenation);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Cart>(), myPagenation);
        }
    }


    @PostMapping(path = "/cart/update")
    public CommonResponseSingle updateCart(@RequestBody CartDto cartDto, HttpServletRequest request) {

        //System.out.println("CONTROLLER");
        //System.out.println(new Gson().toJson(cartDto));

        storeUserDetails(request);

        Cart cart = cartService.update(cartDto);

        if (cart != null) {
            return response(true, HttpStatus.OK, "New cart inserted successfully", cart);
        } else if (cart == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Cart) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Cart) null);
    }

    @DeleteMapping(path = "/cart/{id}")
    public CommonResponseSingle<Cart> deleteCart(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (cartService.deleteById(id)) {
            return response(true, HttpStatus.OK, "Cart by id " + id + " is deleted", (Cart) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "Cart not found or deleted", (Cart) null);
        }
    }

}