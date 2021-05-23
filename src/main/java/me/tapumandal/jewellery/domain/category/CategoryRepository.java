package me.tapumandal.jewellery.domain.category;

import me.tapumandal.jewellery.repository.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT c FROM Category c WHERE isActive = 1")
    List<Category> findAllActive();
}
