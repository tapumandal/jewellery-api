package me.tapumandal.jewellery.domain.cloud_messaging;

import me.tapumandal.jewellery.util.CommonResponseSingle;
import me.tapumandal.jewellery.util.ControllerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/cloud_messaging")
public class CloudMessagingController extends ControllerHelper<CloudMessaging> {

    @Autowired
    CloudMessagingService cloudMessagingService;

    @PostMapping(path = "/consumer/send")
    public CommonResponseSingle createFCMRegistrationToken(@ModelAttribute CloudMessagingDto cloudMessagingDto, HttpServletRequest request) {


        //System.out.println("Cloud Messaging Controller:");
        //System.out.println(new Gson().toJson(cloudMessagingDto));

        storeUserDetails(request);
        CloudMessaging cloudMessaging = cloudMessagingService.create(cloudMessagingDto);

        if (cloudMessaging != null) {
            return response(true, HttpStatus.CREATED, "Notification send successfully", cloudMessaging);
        } else if (cloudMessaging == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (CloudMessaging) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (CloudMessaging) null);
    }

    @GetMapping(path = "/get")
    public CommonResponseSingle getFCMRegistrationToken(HttpServletRequest request) {

        storeUserDetails(request);

        CloudMessaging cloudMessaging = cloudMessagingService.getById(0);

        if (cloudMessaging != null) {
            return response(true, HttpStatus.CREATED, "FCMRegistrationToken inserted successfully", cloudMessaging);
        } else if (cloudMessaging == null) {
            return response(false, HttpStatus.NOT_FOUND, "FCMRegistrationToken not Found", (CloudMessaging) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (CloudMessaging) null);
    }


//    @PostMapping(path = "/update")
//    public CommonResponseSingle updateFCMRegistrationToken(@RequestBody FCMRegistrationTokenDto fcmRegistrationTokenDto, HttpServletRequest request) {
//
//        storeUserDetails(request);
//
//        FCMRegistrationToken fcmRegistrationToken = fcmRegistrationTokenService.update(fcmRegistrationTokenDto);
//
//        if (fcmRegistrationToken != null) {
//            return response(true, HttpStatus.OK, "New fcmRegistrationToken inserted successfully", fcmRegistrationToken);
//        } else if (fcmRegistrationToken == null) {
//            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (FCMRegistrationToken) null);
//        }
//        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (FCMRegistrationToken) null);
//    }


}