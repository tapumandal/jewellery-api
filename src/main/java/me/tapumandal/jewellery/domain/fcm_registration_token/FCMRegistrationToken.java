package me.tapumandal.jewellery.domain.fcm_registration_token;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "fcm_registration_token")
@Component
public class FCMRegistrationToken{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "user_id")
    protected int userId;

    @Column(name = "fcm_registration_token")
    protected String fcmRegistrationToken = "";

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    public FCMRegistrationToken(){}

    public FCMRegistrationToken(FCMRegistrationTokenDto fcmRegistrationTokenDto) {
        this.id = fcmRegistrationTokenDto.getId();
        this.userId = fcmRegistrationTokenDto.getUserId();
        this.fcmRegistrationToken = fcmRegistrationTokenDto.getFcmRegistrationToken();
    }
}
