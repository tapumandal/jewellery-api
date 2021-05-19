package me.tapumandal.jewellery.repository;

import me.tapumandal.jewellery.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    public User findByUsername(String userName);

    @Query("SELECT u FROM User u WHERE u.isActive = 1")
    public User findByIdIfActive(int id);
}
