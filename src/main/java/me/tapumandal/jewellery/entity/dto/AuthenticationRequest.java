package me.tapumandal.jewellery.entity.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthenticationRequest {

    @NotNull(message = "Username can't be empty")
    @Email(message = "Email is not valid")
    private String username;

//    @NotNull(message = "Password can't be empty")
//    @Size(min=6, max = 32, message = "Password is not valid")
    private String password;


    @NotNull(message = "TokenID can't be empty")
    @Size(min=6, message = "TokenID is not valid")
    private String userTokenId;



    public AuthenticationRequest(){}

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserTokenId() {
        return userTokenId == null ? "" : userTokenId;
    }

    public void setUserTokenId(String userTokenId) {
        this.userTokenId = userTokenId;
    }
}
