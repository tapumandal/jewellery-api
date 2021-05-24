package me.tapumandal.jewellery.domain.category;

import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    private Category category;

    public CategoryServiceImpl(){}

    public CategoryServiceImpl(Category category){
        this.category = category;
    }

    @Override
    public Category create(Category entity) {

        Optional<Category> category;

        category = Optional.ofNullable(categoryRepository.save(entity));

        if(category.isPresent()){
            return category.get();
        }else{
            return null;
        }
    }

    @Override
    public Category update(Category entity) {

        Optional<Category> category;
        category = Optional.ofNullable(categoryRepository.save(entity));

        if(category.isPresent()){
            return category.get();
        }else{
            return null;
        }

    }

    @Override
    public List<Category> getAll(Pageable pageable, ListFilter listFilter) {
        return null;
    }

    @Override
    public List<Category> getAll() {
        Optional<List<Category>> categories = Optional.ofNullable(categoryRepository.findAll());

        if(categories.isPresent()){
            return categories.get();
        }else{
            return null;
        }
    }

    @Override
    public Category getById(int id) {

        Optional<Category> category = categoryRepository.findById(id);

        if(category.isPresent()){
            return category.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            categoryRepository.deleteById(id);
        }catch (Exception ex){
            return false;
        }

        return true;
    }

    @Override
    public Category getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Category> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            if(category.get().isActive()){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return category.isDeleted();
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return null;
    }

    @Override
    public int getAllEntityCount(Pageable pageable, ListFilter listFilter) {
        return 0;
    }

}
