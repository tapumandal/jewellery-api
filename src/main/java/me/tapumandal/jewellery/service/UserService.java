package me.tapumandal.jewellery.service;

import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.entity.User;
import me.tapumandal.jewellery.entity.dto.ConsumerUserDto;
import me.tapumandal.jewellery.entity.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService extends Service<User, User>{

    public boolean isUserExist(String userName);
    public User createUser(User user);
    public User createAdminAccount(User user);
    public User getUserByUserName(String username);
    public Page<User> getAllPageable(Pageable pageable);

}
