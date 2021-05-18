package me.tapumandal.jewellery.entity.dto;

import com.sun.istack.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
public class WarehouseDto {


    @Nullable
    private int id;

    @NotEmpty
    @Size(min=2, max = 50, message = "Select a name to identify")
    protected String name;

    protected String phone;

    @NotEmpty
    @Size(min=2, max = 50, message = "Warehouse must have an address")
    protected String address;

    private boolean isActive = true;

    private boolean isDeleted = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

}
