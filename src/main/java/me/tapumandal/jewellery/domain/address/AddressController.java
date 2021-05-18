package me.tapumandal.jewellery.domain.address;

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
@RequestMapping("/api/v1/address")
public class AddressController extends ControllerHelper<Address> {

    @Autowired
    AddressService addressService;

    @PostMapping(path = "/create")
    public CommonResponseSingle createCompany(@ModelAttribute AddressDto addressDto, HttpServletRequest request) {

        storeUserDetails(request);

        Address address = addressService.create(addressDto);

        if (address != null) {
            return response(true, HttpStatus.CREATED, "New address inserted successfully", address);
        } else if (address == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (Address) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Address) null);
    }

    @GetMapping(path = "/{id}")
    public CommonResponseSingle<Address> getCompany(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        Address address = addressService.getById(id);

        if (address != null) {
            return response(true, HttpStatus.FOUND, "Company by id: " + id, address);
        } else if (address == null) {
            return response(false, HttpStatus.NO_CONTENT, "Company not found or deleted", (Address) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (Address) null);
        }
    }

    @GetMapping(path = "/list")
    public CommonResponseArray<Address> getAll(@ModelAttribute ListFilter listFilter, HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<Address> addresss = addressService.getAll(pageable, listFilter);

//        MyPagenation myPagenation = managePagenation(request, addressService.getPageable(pageable), pageable);

        if (!addresss.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All address list", addresss);
        } else if (addresss.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No address found", new ArrayList<Address>());
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Address>());
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateCompany(@RequestBody AddressDto addressDto, HttpServletRequest request) {

        storeUserDetails(request);

        Address address = addressService.update(addressDto);

        if (address != null) {
            return response(true, HttpStatus.OK, "New address inserted successfully", address);
        } else if (address == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Address) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Address) null);
    }

    @DeleteMapping(path = "/{id}")
    public CommonResponseSingle<Address> deleteCompany(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (addressService.deleteById(id)) {
            return response(true, HttpStatus.OK, "Company by id " + id + " is deleted", (Address) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "Company not found or deleted", (Address) null);
        }
    }

}