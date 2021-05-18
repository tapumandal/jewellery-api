package me.tapumandal.jewellery.domain.cloud_messaging;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "cloud_messaging")
@Component
public class CloudMessaging {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "notification_title")
    protected String notificationTitle = "";

    @Column(name = "notification_text", length = 300)
    protected String notificationText = "";

    @Column(name = "notification_image")
    protected String notificationImage = "";

    @Column(name = "notification_name_optional")
    protected String notificationNameOptional = "";

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public CloudMessaging(){}

    public CloudMessaging(CloudMessagingDto fcmRegistrationTokenDto) {
        this.id = fcmRegistrationTokenDto.getId();
        this.notificationTitle = fcmRegistrationTokenDto.getNotificationTitle();
        this.notificationText = fcmRegistrationTokenDto.getNotificationText();
        this.notificationImage = fcmRegistrationTokenDto.getNotificationImage();
        this.notificationNameOptional = fcmRegistrationTokenDto.getNotificationNameOptional();
    }
}
