package me.tapumandal.jewellery.service;

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
    UserRepositoryImpl userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getByUserName(username);
        return new MyUserDetails(user);
    }
    
}