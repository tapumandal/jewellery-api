package me.tapumandal.jewellery.entity.dto;

import com.sun.istack.Nullable;
import lombok.Data;
import me.tapumandal.jewellery.domain.address.AddressDto;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
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
}
