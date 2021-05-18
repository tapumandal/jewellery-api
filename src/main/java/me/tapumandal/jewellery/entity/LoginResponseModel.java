package me.tapumandal.jewellery.entity;

public class LoginResponseModel {

    private String jwt;

    private User user;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}