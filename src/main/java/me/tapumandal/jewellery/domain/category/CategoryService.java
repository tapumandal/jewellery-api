package me.tapumandal.jewellery.domain.category;

import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.service.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService extends Service<Category, Category> {

    public List<Category> getAll();
}
