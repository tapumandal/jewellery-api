package me.tapumandal.jewellery.controller.v1;

import me.tapumandal.jewellery.util.CommonResponseArray;
import me.tapumandal.jewellery.util.CommonResponseSingle;
import me.tapumandal.jewellery.util.ControllerHelper;
import me.tapumandal.jewellery.util.MyPagenation;
import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.entity.Warehouse;
import me.tapumandal.jewellery.entity.dto.WarehouseDto;
import me.tapumandal.jewellery.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController extends ControllerHelper<Warehouse> {

    @Autowired
    WarehouseService warehouseService;

    @PostMapping(path = "/create")
    public CommonResponseSingle createWarehouse(@RequestBody @Valid WarehouseDto warehouseDto, HttpServletRequest request) {

        storeUserDetails(request);

        Warehouse warehouse = warehouseService.create(warehouseDto);

        if (warehouse != null) {
            return response(true, HttpStatus.CREATED, "New warehouse created successfully", warehouse);
        } else if (warehouse == null) {
            return response(false, HttpStatus.NOT_ACCEPTABLE, "Something is wrong with data", (Warehouse) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Warehouse) null);
    }

    @GetMapping(path = "/{id}")
    public CommonResponseSingle<Warehouse> getProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        Warehouse warehouse = warehouseService.getById(id);

        if (warehouse != null) {
            return response(true, HttpStatus.FOUND, "Warehouse by id: " + id, warehouse);
        } else if (warehouse == null) {
            return response(false, HttpStatus.NO_CONTENT, "Warehouse not found or deleted", (Warehouse) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (Warehouse) null);
        }
    }

    @GetMapping(path = "/list")
    public CommonResponseArray<Warehouse> getAll(@ModelAttribute ListFilter listFilter, HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<Warehouse> products = warehouseService.getAll(pageable, listFilter);

        MyPagenation myPagenation = managePagenation(request, warehouseService.getAllEntityCount(pageable, listFilter), pageable);

        if (!products.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All warehouse list", products, myPagenation);
        } else if (products.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No warehouse found", new ArrayList<Warehouse>(), myPagenation);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Warehouse>(), myPagenation);
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateProduct(@RequestBody WarehouseDto warehouseDto, HttpServletRequest request) {

        storeUserDetails(request);

        Warehouse warehouse = warehouseService.update(warehouseDto);

        if (warehouse != null) {
            return response(true, HttpStatus.OK, "New warehouse inserted successfully", warehouse);
        } else if (warehouse == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Warehouse) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Warehouse) null);
    }

    @DeleteMapping(path = "/{id}")
    public CommonResponseSingle<Warehouse> deleteProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (warehouseService.deleteById(id)) {
            return response(true, HttpStatus.OK, "Warehouse by id " + id + " is deleted", (Warehouse) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "Warehouse not found or deleted", (Warehouse) null);
        }
    }

}