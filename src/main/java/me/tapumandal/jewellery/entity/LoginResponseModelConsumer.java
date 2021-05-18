package me.tapumandal.jewellery.entity;

import me.tapumandal.jewellery.entity.dto.ConsumerUserDto;

public class LoginResponseModelConsumer {

    private String jwt;

    private ConsumerUserDto user;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public ConsumerUserDto getUser() {
        return user;
    }

    public void setUser(ConsumerUserDto user) {
        this.user = user;
    }
}