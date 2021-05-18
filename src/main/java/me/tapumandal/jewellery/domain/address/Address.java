package me.tapumandal.jewellery.domain.address;
import javax.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column( name = "name" )
    private String name;

    @Column( name = "mobileNo" )
    private String mobileNo;

    @Column( name = "area" )
    private String area;

    @Column( name = "block" )
    private String block;

    @Column( name = "road" )
    private String road;

    @Column( name = "house" )
    private String house;

    @Column( name = "flat" )
    private String flat;

    @Column( name = "details", length = 500)
    private String details;

    @Column(name = "is_active", columnDefinition = "boolean default 1")
    private boolean isActive = true;

    @Column(name = "is_deleted", columnDefinition = "boolean default 0")
    private boolean isDeleted = false;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    public Address() {
    }

    public Address(AddressDto addressDto) {
        this.name = addressDto.getName();
        this.mobileNo = addressDto.getMobileNo();
        this.area = addressDto.getArea();
        this.block = addressDto.getBlock();
        this.road = addressDto.getRoad();
        this.house = addressDto.getHouse();
        this.flat = addressDto.getFlat();
        this.details = addressDto.getDetails();
    }

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}