package me.tapumandal.jewellery.entity.dto;

import com.sun.istack.Nullable;
import lombok.Data;
import me.tapumandal.jewellery.domain.address.AddressDto;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDto{
    @NotNull
    private int id;

    @NotNull(message = "First Name can't be empty")
    @Size(min=4, max = 32, message = "Write a proper name")
    protected String firstName;

    @NotNull(message = "Last Name can't be empty")
    @Size(min=4, max = 32, message = "Write a proper name")
    protected String lastName;

    @NotNull(message = "UserName can't be empty")
    protected String username;

    @NotNull(message = "Email No can't be empty")
    protected String email;

    @NotNull(message = "Phone Code can't be empty")
    protected String phoneNumberCode;

    @NotNull(message = "Phone No can't be empty")
    protected String phoneNumber;

    protected String password;

    protected String work_title;

    protected String role;

    protected boolean is_active = true;

    protected boolean is_deleted = false;

    public UserDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName == null ? "" : firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName == null ? "" : lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username == null ? "" : username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email == null ? "" : email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumberCode() {
        return phoneNumberCode == null ? "" : phoneNumberCode;
    }

    public void setPhoneNumberCode(String phoneNumberCode) {
        this.phoneNumberCode = phoneNumberCode;
    }

    public String getPhoneNumber() {
        return phoneNumber == null ? "" : phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWork_title() {
        return work_title == null ? "" : work_title;
    }

    public void setWork_title(String work_title) {
        this.work_title = work_title;
    }

    public String getRole() {
        return role == null ? "" : role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
