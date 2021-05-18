package me.tapumandal.jewellery.domain.business_settings;

import me.tapumandal.jewellery.util.CommonResponseSingle;
import me.tapumandal.jewellery.util.ControllerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/business_settings")
public class BusinessSettingsController extends ControllerHelper<BusinessSettings> {

    @Autowired
    BusinessSettingsService businessSettingsService;

    @PostMapping(path = "/create")
    public CommonResponseSingle createBusinessSettings(@RequestBody BusinessSettingsDto businessSettingsDto, HttpServletRequest request) {


        //System.out.println("createBusinessSettingsDTO:");
        //System.out.println(new Gson().toJson(businessSettingsDto.getVersionControlDto()));
        //System.out.println(new Gson().toJson(businessSettingsDto));

        storeUserDetails(request);
        BusinessSettings businessSettings = businessSettingsService.create(businessSettingsDto);

        if (businessSettings != null) {
            return response(true, HttpStatus.CREATED, "New businessSettings inserted successfully", businessSettings);
        } else if (businessSettings == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (BusinessSettings) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (BusinessSettings) null);
    }

    @GetMapping(path = "/get")
    public CommonResponseSingle getBusinessSettings(HttpServletRequest request) {

        storeUserDetails(request);

        BusinessSettings businessSettings = businessSettingsService.getById(0);

        if (businessSettings != null) {
            return response(true, HttpStatus.CREATED, "BusinessSettings inserted successfully", businessSettings);
        } else if (businessSettings == null) {
            return response(false, HttpStatus.NOT_FOUND, "BusinessSettings not Found", (BusinessSettings) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (BusinessSettings) null);
    }


//    @PostMapping(path = "/update")
//    public CommonResponseSingle updateBusinessSettings(@RequestBody BusinessSettingsDto businessSettingsDto, HttpServletRequest request) {
//
//        storeUserDetails(request);
//
//        BusinessSettings businessSettings = businessSettingsService.update(businessSettingsDto);
//
//        if (businessSettings != null) {
//            return response(true, HttpStatus.OK, "New businessSettings inserted successfully", businessSettings);
//        } else if (businessSettings == null) {
//            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (BusinessSettings) null);
//        }
//        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (BusinessSettings) null);
//    }


}