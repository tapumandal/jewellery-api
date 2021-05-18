package me.tapumandal.jewellery.domain.product;

import me.tapumandal.jewellery.repository.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository extends Repository<Product> {

    public List<ProductBusiness> getAllBusiness(Pageable pageable, String flag, String selectedParentMenu);
    public int getAllBusinessEntityCount(Pageable pageable, String flag);

    List<ProductBusiness> searchProduct(Pageable pageable, String query);
    public int searchProductEntityCount(Pageable pageable, String query);
}
