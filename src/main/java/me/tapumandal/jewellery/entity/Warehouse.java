package me.tapumandal.jewellery.entity;

import me.tapumandal.jewellery.entity.dto.WarehouseDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "warehouse")
public class Warehouse {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "name")
    protected String name;

    @Column(name = "phone")
    protected String phone;

    @Column(name = "address")
    protected String address;

    @Column(name = "is_active", columnDefinition = "boolean default 1")
    private boolean isActive = true;

    @Column(name = "is_deleted", columnDefinition = "boolean default 0")
    private boolean isDeleted = false;

    @Column(name = "created_at", updatable=false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public Warehouse() {}

    public Warehouse(WarehouseDto warehouseDto) {
        this.id = warehouseDto.getId();
        this.name = warehouseDto.getName();
        this.phone = warehouseDto.getPhone();
        this.address = warehouseDto.getAddress();
        this.isActive = warehouseDto.isActive();
        this.isDeleted = warehouseDto.isDeleted();
    }

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
}
