package me.tapumandal.jewellery.service;

import me.tapumandal.jewellery.entity.User;
import me.tapumandal.jewellery.entity.dto.ConsumerUserDto;
import me.tapumandal.jewellery.entity.dto.UserDto;

public interface UserService extends Service<User, ConsumerUserDto>{

    public boolean isUserExist(String userName);
    public ConsumerUserDto createUser(User user);
    public ConsumerUserDto createAdminAccount(User user);
    public ConsumerUserDto getUserByUserName(String username);

}
