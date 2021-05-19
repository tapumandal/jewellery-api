package me.tapumandal.jewellery.service;

import me.tapumandal.jewellery.repository.UserRepository;
import me.tapumandal.jewellery.repository.implementation.UserRepositoryImpl;
import me.tapumandal.jewellery.entity.MyUserDetails;
import me.tapumandal.jewellery.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getUserByUserName(username);
        if(userService.isActive(user.getId())){
            return new MyUserDetails(user);
        }

        return new MyUserDetails(null);

    }
    
}