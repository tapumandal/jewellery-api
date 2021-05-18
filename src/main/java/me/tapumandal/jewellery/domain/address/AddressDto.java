package me.tapumandal.jewellery.domain.address;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

public class AddressDto {

    protected int id;

    private String name;

    private String mobileNo;

    private String area;

    private String block;

    private String road;

    private String house;

    private String flat;

    private String details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo == null ? "" : mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getArea() {
        return area == null ? "" : area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBlock() {
        return block == null ? "" : block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getRoad() {
        return road == null ? "" : road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getHouse() {
        return house == null ? "" : house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat == null ? "" : flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getDetails() {
        return details == null ? "" : details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}