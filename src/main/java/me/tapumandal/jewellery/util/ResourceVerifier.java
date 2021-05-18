package me.tapumandal.jewellery.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import me.tapumandal.jewellery.domain.product.ProductService;
import me.tapumandal.jewellery.service.UserService;
import me.tapumandal.jewellery.service.WarehouseService;


@Component
public class ResourceVerifier {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    WarehouseService warehouseService;


    public boolean checkProduct(int id) {
        if(!productService.isActive(id)){
            return false;
        }
        return true;
    }

    public boolean checkUser(int id) {
        if(!userService.isActive(id)){
            return false;
        }
        return true;
    }

    public boolean checkWarehouse(int id) {
        if(!warehouseService.isActive(id)){
            return false;
        }
        return true;
    }

}


