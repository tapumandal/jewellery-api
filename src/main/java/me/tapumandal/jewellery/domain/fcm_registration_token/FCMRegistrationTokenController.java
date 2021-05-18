package me.tapumandal.jewellery.domain.fcm_registration_token;

import me.tapumandal.jewellery.util.CommonResponseSingle;
import me.tapumandal.jewellery.util.ControllerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/fcm_registration_token")
public class FCMRegistrationTokenController extends ControllerHelper<FCMRegistrationToken> {

    @Autowired
    FCMRegistrationTokenService fcmRegistrationTokenService;

    @PostMapping(path = "/consumer/create")
    public CommonResponseSingle createFCMRegistrationToken(@RequestBody FCMRegistrationTokenDto fcmRegistrationTokenDto, HttpServletRequest request) {


        //System.out.println("createFCMRegistrationTokenDTO:");
        //System.out.println(new Gson().toJson(fcmRegistrationTokenDto));

        storeUserDetails(request);
        FCMRegistrationToken fcmRegistrationToken = fcmRegistrationTokenService.create(fcmRegistrationTokenDto);

        if (fcmRegistrationToken != null) {
            return response(true, HttpStatus.CREATED, "New fcmRegistrationToken inserted successfully", fcmRegistrationToken);
        } else if (fcmRegistrationToken == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (FCMRegistrationToken) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (FCMRegistrationToken) null);
    }

//    @GetMapping(path = "/get")
//    public CommonResponseSingle getFCMRegistrationToken(HttpServletRequest request) {
//
//        storeUserDetails(request);
//
//        FCMRegistrationToken fcmRegistrationToken = fcmRegistrationToken.getById(0);
//
//        if (fcmRegistrationToken != null) {
//            return response(true, HttpStatus.CREATED, "FCMRegistrationToken inserted successfully", fcmRegistrationToken);
//        } else if (fcmRegistrationToken == null) {
//            return response(false, HttpStatus.NOT_FOUND, "FCMRegistrationToken not Found", (FCMRegistrationToken) null);
//        }
//        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (FCMRegistrationToken) null);
//    }


}