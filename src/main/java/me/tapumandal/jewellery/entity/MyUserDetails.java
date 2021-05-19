package me.tapumandal.jewellery.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public class MyUserDetails implements UserDetails {

    protected String username;
    protected String password;
    protected String phoneNumberCode;
    protected String phoneNumber;
    protected boolean active;
    protected List<SimpleGrantedAuthority> authrities;

    public MyUserDetails(){}

    public MyUserDetails(User user){
        this.username = user.getUsername();
        this.phoneNumberCode = user.getPhoneNumberCode();
        this.phoneNumber = user.getPhoneNumber();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authrities = Arrays.stream(user.getRole().split(","))
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());

    }
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return authrities;
    }

    @Override
    public String getPassword() {
        
        return password;
    }

    @Override
    public String getUsername() {
        
        return username;
    }



    @Override
    public boolean isAccountNonExpired() {
        
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        
        return true;
    }

    @Override
    public boolean isEnabled() {
        
        return true;
    }
    
}