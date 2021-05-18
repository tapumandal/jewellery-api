package me.tapumandal.jewellery.entity.dto;

import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class AdminUserDto{

    @Valid
    private UserDto userDto;

    public AdminUserDto(){};

    public AdminUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

}
