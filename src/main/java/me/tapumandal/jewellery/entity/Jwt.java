package me.tapumandal.jewellery.entity;

public class Jwt {

    private String jwt;

    public Jwt(){}

    public Jwt(String jwt){
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
