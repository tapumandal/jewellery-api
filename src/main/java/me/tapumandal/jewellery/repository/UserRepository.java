package me.tapumandal.jewellery.repository;

import me.tapumandal.jewellery.entity.User;

public interface
UserRepository extends Repository<User>{

    public boolean isUserExist(String userName);
    public User getUserByUserName(String username);
    public boolean foundRefCode(String refCode);
}
