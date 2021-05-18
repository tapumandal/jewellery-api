package me.tapumandal.jewellery.domain.appnavigation;

import me.tapumandal.jewellery.util.CommonResponseArray;
import me.tapumandal.jewellery.util.CommonResponseSingle;
import me.tapumandal.jewellery.util.ControllerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/navigation")
public class NavigationController extends ControllerHelper<Navigation> {

    @Autowired
    NavigationService navigationService;

//    @PostMapping(path = "/create")
//    public CommonResponseSingle createNavigation(@ModelAttribute NavigationDto navigationDto, HttpServletRequest request) {
//
//
//        storeUserDetails(request);
//        Navigation navigation = navigationService.create(navigationDto);
//
//        if (navigation != null) {
//            return response(true, HttpStatus.CREATED, "New navigation inserted successfully", navigation);
//        } else if (navigation == null) {
//            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (Navigation) null);
//        }
//        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Navigation) null);
//    }
    @PostMapping(path = "/create")
    public CommonResponseSingle createNavigation(@RequestBody NavigationDto navigationDto, HttpServletRequest request) {


        //System.out.println("HEY");
        //System.out.println(new Gson().toJson(navigationDto));

        storeUserDetails(request);
        Navigation navigation = navigationService.create(navigationDto);

        if (navigation != null) {
            return response(true, HttpStatus.CREATED, "New navigation inserted successfully", navigation);
        } else if (navigation == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (Navigation) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Navigation) null);
    }

    @GetMapping(path = "/get")
    public CommonResponseArray getNavigation(HttpServletRequest request) {

        storeUserDetails(request);

        List<MenuList> menuLists = navigationService.getNavigation();


        if (menuLists != null) {
            return responseCustom(true, HttpStatus.CREATED, "New navigation inserted successfully", menuLists);
        } else if (menuLists == null) {
            return responseCustom(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (List<MenuList>) null);
        }
        return responseCustom(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (List<MenuList>) null);
    }

    @PostMapping(path = "/update")
    public CommonResponseSingle updateNavigation(@RequestBody NavigationDto navigationDto, HttpServletRequest request) {

        storeUserDetails(request);

        Navigation navigation = navigationService.update(navigationDto);

        if (navigation != null) {
            return response(true, HttpStatus.OK, "New navigation inserted successfully", navigation);
        } else if (navigation == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Navigation) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Navigation) null);
    }

    @Autowired
    private   CommonResponseArray commonResponseArray;

    protected  CommonResponseArray<List<MenuList>> responseCustom(boolean action, HttpStatus status, String message, List<MenuList> data){

        commonResponseArray.setAction(action);
        commonResponseArray.setStatus(status);
        commonResponseArray.setMessage(message);
        commonResponseArray.setData(data);

        return commonResponseArray;
    }

}