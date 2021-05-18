package me.tapumandal.jewellery.domain.cart;

import org.springframework.data.domain.Pageable;
import me.tapumandal.jewellery.repository.Repository;

import java.util.List;

public interface CartRepository extends Repository<Cart> {

    public Cart getCartFirstTime(int cartId);
    public List<Cart> getAllByUserID(int userID, Pageable pageable);
    public int getAllByIDEntityCount(Pageable pageable, int id);
}
