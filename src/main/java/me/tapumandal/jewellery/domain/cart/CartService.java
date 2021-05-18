package me.tapumandal.jewellery.domain.cart;

import me.tapumandal.jewellery.service.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartService extends Service<CartDto, Cart> {
    public List<Cart> getAllByUserID(Pageable pageable);
    public int getAllByIDEntityCount(Pageable pageable, int id);
}
