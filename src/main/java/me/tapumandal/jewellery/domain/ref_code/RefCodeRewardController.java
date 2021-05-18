package me.tapumandal.jewellery.domain.ref_code;

import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.util.CommonResponseArray;
import me.tapumandal.jewellery.util.CommonResponseSingle;
import me.tapumandal.jewellery.util.ControllerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/refCodeReward")
public class RefCodeRewardController extends ControllerHelper<RefCodeReward> {

    @Autowired
    RefCodeRewardService refCodeRewardService;

    @PostMapping(path = "/create")
    public CommonResponseSingle createRefCodeReward(@ModelAttribute RefCodeRewardDto refCodeRewardDto, HttpServletRequest request) {

        //System.out.println(new Gson().toJson(request.getAttribute("name")));
        //System.out.println(new Gson().toJson(refCodeRewardDto));

        storeUserDetails(request);

        RefCodeReward refCodeReward = refCodeRewardService.create(refCodeRewardDto);

        if (refCodeReward != null) {
            return response(true, HttpStatus.CREATED, "New refCodeReward inserted successfully", refCodeReward);
        } else if (refCodeReward == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (RefCodeReward) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (RefCodeReward) null);
    }

    @GetMapping(path = "/{id}")
    public CommonResponseSingle<RefCodeReward> getRefCodeReward(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        RefCodeReward refCodeReward = refCodeRewardService.getById(id);

        if (refCodeReward != null) {
            return response(true, HttpStatus.FOUND, "RefCodeReward by id: " + id, refCodeReward);
        } else if (refCodeReward == null) {
            return response(false, HttpStatus.NO_CONTENT, "RefCodeReward not found or deleted", (RefCodeReward) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (RefCodeReward) null);
        }
    }

    @GetMapping(path = "/list")
    public CommonResponseArray<RefCodeReward> getAll(@ModelAttribute ListFilter listFilter, HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<RefCodeReward> refCodeRewards = refCodeRewardService.getAll(pageable, listFilter);

//        MyPagenation myPagenation = managePagenation(request, refCodeRewardService.getPageable(pageable), pageable);

        if (!refCodeRewards.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All refCodeReward list", refCodeRewards);
        } else if (refCodeRewards.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No refCodeReward found", new ArrayList<RefCodeReward>());
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<RefCodeReward>());
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateRefCodeReward(@RequestBody RefCodeRewardDto refCodeRewardDto, HttpServletRequest request) {

        storeUserDetails(request);

        RefCodeReward refCodeReward = refCodeRewardService.update(refCodeRewardDto);

        if (refCodeReward != null) {
            return response(true, HttpStatus.OK, "New refCodeReward inserted successfully", refCodeReward);
        } else if (refCodeReward == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (RefCodeReward) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (RefCodeReward) null);
    }

    @DeleteMapping(path = "/{id}")
    public CommonResponseSingle<RefCodeReward> deleteRefCodeReward(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (refCodeRewardService.deleteById(id)) {
            return response(true, HttpStatus.OK, "RefCodeReward by id " + id + " is deleted", (RefCodeReward) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "RefCodeReward not found or deleted", (RefCodeReward) null);
        }
    }

}