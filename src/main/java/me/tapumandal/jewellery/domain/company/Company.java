package me.tapumandal.jewellery.domain.company;

import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Entity
@Table(name = "company")
@Component
public class Company {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "name")
    protected String name;

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

    public Company(){}

    public Company(CompanyDto companyDto) {
        this.id = companyDto.getId();
        this.name = companyDto.getName();
        this.isActive = companyDto.isActive();
        this.isDeleted = companyDto.isDeleted();

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
