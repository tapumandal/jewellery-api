package me.tapumandal.jewellery.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import me.tapumandal.jewellery.entity.dto.UserDto;
import me.tapumandal.jewellery.domain.address.Address;
import me.tapumandal.jewellery.domain.address.AddressDto;
import me.tapumandal.jewellery.domain.ref_code.RefCodeReward;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;


    @Column(name = "first_name")
    @NotNull(message = "First Name can't be empty")
    protected String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last Name can't be empty")
    protected String lastName;

    @Column(name = "email", unique = true)
    @NotNull(message = "Email No can't be empty")
    protected String email;

    @Column(name = "phone_number_code")
    protected String phoneNumberCode;

    @Column(name = "phone_number")
    protected String phoneNumber;

    @Column(name = "username", unique = true)
    protected String username;

    @Column(name = "username_status")
    protected boolean isUsernameVerified;

    @Column(name = "username_type")
    protected String usernameType;

    @Column(name = "gender")
    protected String gender;

    @Column(name = "password")
    @NotNull(message = "password No can't be empty")
    @Size(min=6, max = 32, message = "Password size must be between 6 and 32 character.")
    protected String password;

    @Column(name = "work_title")
    protected String workTitle;

    @Column(name = "role")
    protected String role;


    @Column(name = "is_active", columnDefinition = "boolean default 1")
    private boolean isActive = false;

    @Column(name = "is_deleted", columnDefinition = "boolean default 0")
    private boolean isDeleted = false;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Address> addresses = new ArrayList<Address>();

    public User(User user) {

    }

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "ref_code_id", referencedColumnName = "id")
//    private RefCodeReward refCodeReward;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_promo_id", referencedColumnName = "id")
//    private UserPromo userPromo;
}